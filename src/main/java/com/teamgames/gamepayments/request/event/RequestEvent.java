package com.teamgames.gamepayments.request.event;

import com.teamgames.gamepayments.request.Request;
import com.teamgames.gamepayments.request.event.processor.RequestEventProcessor;
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

    private RequestEventPhase phase = RequestEventPhase.IDLE;

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
        if (phase == RequestEventPhase.IDLE) {
            phase = RequestEventPhase.REQUESTING;
            futureResult = processor.getService().submit(() -> request.create(processor.getHttpClient()));
        } else if (phase == RequestEventPhase.REQUESTING) {
            if (futureResult == null) {
                eventResult = RequestEventResult.FAILED_ERRONEOUSLY;
                return;
            }
            if (futureResult.isCancelled()) {
                eventResult = RequestEventResult.FAILED_ERRONEOUSLY;
                return;
            }
            if (futureResult.isDone()) {
                try {
                    result = futureResult.get();
                    phase = RequestEventPhase.COMPLETE;
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
