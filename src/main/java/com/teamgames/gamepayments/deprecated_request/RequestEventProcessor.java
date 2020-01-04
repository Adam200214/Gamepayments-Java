package com.teamgames.gamepayments.deprecated_request;

import com.teamgames.gamepayments.deprecated_request.result.Result;
import com.teamgames.gamepayments.deprecated_request.result.listener.impl.ResultErrorListener;
import com.teamgames.gamepayments.deprecated_request.result.listener.impl.ResultOkListener;
import com.teamgames.gamepayments.deprecated_request.result.listener.impl.ResultTimedOutListener;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Jason MK on 2020-01-02 at 2:46 p.m.
 */
public class RequestEventProcessor<T extends Result, R extends Request<T>> {

    private final Queue<RequestEvent<T, R>> events = new ConcurrentLinkedDeque<>();

    private final ExecutorService service = Executors.newFixedThreadPool(Math.max(1, Runtime.getRuntime().availableProcessors() / 2));

    private final ResultErrorListener<T, R> errorListener;

    private final ResultOkListener<T, R> okListener;

    private final ResultTimedOutListener<T, R> timedOutListener;

    public RequestEventProcessor(ResultErrorListener<T, R> errorListener, ResultOkListener<T, R> okListener, ResultTimedOutListener<T, R> timedOutListener) {
        this.errorListener = errorListener;
        this.okListener = okListener;
        this.timedOutListener = timedOutListener;
    }

    public void process() {
        Iterator<RequestEvent<T, R>> iterator = events.iterator();

        while (iterator.hasNext()) {
            RequestEvent<T, R> event = iterator.next();

            R request = event.getRequest();

            RequestEventResult eventResult = event.getEventResult();

            if (eventResult == RequestEventResult.NONE) {
                continue;
            }
            iterator.remove();

            if (eventResult == RequestEventResult.OK) {
                T result = event.getResult();

                if (result == null) {
                    //TODO this should not happen
                    continue;
                }
                okListener.listen(request, result);
            } else if (eventResult == RequestEventResult.TIMED_OUT) {
                timedOutListener.listen(request, null);
            } else if (eventResult == RequestEventResult.FAILED_ERRONEOUSLY) {
                errorListener.listen(request, null);
            }
        }
    }

    public ExecutorService getService() {
        return service;
    }
}
