package com.teamgames.gamepayments.request.event.processor;

import com.teamgames.gamepayments.request.Request;
import com.teamgames.gamepayments.request.event.RequestEvent;
import com.teamgames.gamepayments.request.event.RequestEventResult;
import com.teamgames.gamepayments.request.result.Result;
import com.teamgames.gamepayments.request.result.listener.impl.ResultErrorListener;
import com.teamgames.gamepayments.request.result.listener.impl.ResultOkListener;
import com.teamgames.gamepayments.request.result.listener.impl.ResultTimeoutListener;
import org.apache.http.client.fluent.Executor;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Jason MK on 2020-01-02 at 2:46 p.m.
 */
public class RequestEventProcessor<T extends Result, R extends Request<T>> {

    private final Queue<RequestEvent<T, R>> events = new ArrayDeque<>();

    private final ExecutorService service = Executors.newFixedThreadPool(Math.max(1, Runtime.getRuntime().availableProcessors() / 2));

    private final List<ResultErrorListener<T, R>> errorListeners = new ArrayList<>();

    private final List<ResultOkListener<T, R>> okListeners = new ArrayList<>();

    private final List<ResultTimeoutListener<T, R>> timeoutListeners = new ArrayList<>();

    private final Executor httpClient;

    public RequestEventProcessor(Executor httpClient) {
        this.httpClient = httpClient;
    }

    public void submit(RequestEvent<T, R> request) {
        events.offer(request);
    }

    public void addErrorListener(ResultErrorListener<T, R> errorListener) {
        errorListeners.add(errorListener);
    }

    public void addOkListener(ResultOkListener<T, R> okListener) {
        okListeners.add(okListener);
    }

    public void addTimeoutListener(ResultTimeoutListener<T, R> timeoutListener) {
        timeoutListeners.add(timeoutListener);
    }

    public void process() {
        System.out.println("Processing " + events.size());
        Iterator<RequestEvent<T, R>> iterator = events.iterator();

        while (iterator.hasNext()) {
            RequestEvent<T, R> event = iterator.next();

            R request = event.getRequest();

            RequestEventResult eventResult = event.getEventResult();

            if (eventResult == RequestEventResult.NONE) {
                event.poll(this);
                continue;
            }
            iterator.remove();

            if (eventResult == RequestEventResult.OK) {
                T result = event.getResult();

                if (result == null) {
                    //TODO this should not happen
                    continue;
                }
                okListeners.forEach(listener -> listener.listen(request, result));
            } else if (eventResult == RequestEventResult.TIMED_OUT) {
                timeoutListeners.forEach(listener -> listener.listen(request, null));
            } else if (eventResult == RequestEventResult.FAILED_ERRONEOUSLY) {
                errorListeners.forEach(listener -> listener.listen(request, null));
            }
        }
    }

    public Executor getHttpClient() {
        return httpClient;
    }

    public ExecutorService getService() {
        return service;
    }
}
