package com.teamgames.gamepayments.request.event.processor.impl;

import com.teamgames.gamepayments.request.event.processor.RequestEventProcessor;
import com.teamgames.gamepayments.request.impl.claim.ClaimRequest;
import com.teamgames.gamepayments.request.result.impl.ClaimRequestResult;
import com.teamgames.gamepayments.request.result.listener.impl.ResultErrorListener;
import com.teamgames.gamepayments.request.result.listener.impl.ResultOkListener;
import com.teamgames.gamepayments.request.result.listener.impl.ResultTimedOutListener;
import org.apache.http.client.fluent.Executor;

/**
 * Created by Jason MK on 2020-01-02 at 3:38 p.m.
 */
public class ClaimProcessor extends RequestEventProcessor<ClaimRequestResult, ClaimRequest> {

    public ClaimProcessor(Executor httpClient, ResultErrorListener<ClaimRequestResult, ClaimRequest> errorListener, ResultOkListener<ClaimRequestResult, ClaimRequest> okListener, ResultTimedOutListener<ClaimRequestResult, ClaimRequest> timedOutListener) {
        super(httpClient, errorListener, okListener, timedOutListener);
    }
}
