package com.teamgames.gamepayments.request;

import com.teamgames.gamepayments.request.result.Result;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Jason MK on 2020-01-02 at 2:57 p.m.
 */
public class RequestEvent<T extends Result, R extends Request<T>> {
    
    private final R request;

    private final LocalDateTime timestamp;

    private final Duration maximumDuration;

    private RequestPhase phase = RequestPhase.IDLE;

    private Future<T> futureResult;

    private T result;

    private RequestEventResult eventResult = RequestEventResult.NONE;

    public RequestEvent(R request, Duration maximumDuration) {
        this.request = request;
        this.maximumDuration = maximumDuration;
        this.timestamp = LocalDateTime.now();
    }

    public void poll(RequestEventProcessor<T, R> processor) {
        if (eventResult != null) {
            return;
        }
        LocalDateTime now = LocalDateTime.now();

        if (now.isAfter(timestamp.plus(maximumDuration))) {
            eventResult = RequestEventResult.TIMED_OUT;
            return;
        }
        if (phase == RequestPhase.IDLE) {
            phase = RequestPhase.REQUESTING;
            futureResult = processor.getService().submit(request);
        } else if (phase == RequestPhase.REQUESTING) {
            if (futureResult == null || futureResult.isCancelled()) {
                eventResult = RequestEventResult.FAILED_ERRONEOUSLY;
            } else if (futureResult.isDone()) {
                try {
                    result = futureResult.get();
                    phase = RequestPhase.COMPLETE;
                } catch (InterruptedException | ExecutionException e) {
                    eventResult = RequestEventResult.FAILED_ERRONEOUSLY;
                    e.printStackTrace();
                }
            }
        }
    }

    public R getRequest() {
        return request;
    }

    public T getResult() {
        return result;
    }

    public RequestEventResult getEventResult() {
        return eventResult;
    }
}
