package com.teamgames.gamepayments.service;

import com.google.inject.Inject;
import com.teamgames.gamepayments.request.event.RequestEvent;
import com.teamgames.gamepayments.request.event.processor.impl.ClaimProcessor;
import com.teamgames.gamepayments.request.impl.claim.ClaimRequest;
import com.teamgames.gamepayments.request.result.impl.ClaimRequestResult;
import com.teamgames.gamepayments.request.result.listener.impl.ResultErrorListener;
import com.teamgames.gamepayments.request.result.listener.impl.ResultOkListener;
import com.teamgames.gamepayments.request.result.listener.impl.ResultTimeoutListener;

import java.time.Duration;

/**
 * Created by Jason MK on 2020-01-02 at 1:11 p.m.
 */
public class ClaimService {

    private final ClaimProcessor claimProcessor;

    @Inject
    public ClaimService(ClaimProcessor claimProcessor) {
        this.claimProcessor = claimProcessor;
    }

    public void run() {
        claimProcessor.process();
    }

    public void submit(String apiKey, String username, Duration maximumDuration) {
        claimProcessor.submit(new RequestEvent<>(new ClaimRequest(apiKey, username), maximumDuration));
    }

    public void submit(RequestEvent<ClaimRequestResult, ClaimRequest> request) {
        claimProcessor.submit(request);
    }

    public void addTimeoutListener(ResultTimeoutListener<ClaimRequestResult, ClaimRequest> timeoutListener) {
        claimProcessor.addTimeoutListener(timeoutListener);
    }

    public void addOkListener(ResultOkListener<ClaimRequestResult, ClaimRequest> okListener) {
        claimProcessor.addOkListener(okListener);
    }

    public void addErrorListener(ResultErrorListener<ClaimRequestResult, ClaimRequest> errorListener) {
        claimProcessor.addErrorListener(errorListener);
    }
}
