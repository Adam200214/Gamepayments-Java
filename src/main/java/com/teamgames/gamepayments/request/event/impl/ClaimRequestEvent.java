package com.teamgames.gamepayments.request.event.impl;

import com.teamgames.gamepayments.request.event.RequestEvent;
import com.teamgames.gamepayments.request.impl.claim.ClaimRequest;
import com.teamgames.gamepayments.request.result.impl.ClaimRequestResult;

import java.time.Duration;

/**
 * Created by Jason MK on 2020-01-02 at 3:59 p.m.
 */
public class ClaimRequestEvent extends RequestEvent<ClaimRequestResult, ClaimRequest> {

    public ClaimRequestEvent(ClaimRequest request, Duration maximumDuration) {
        super(request, maximumDuration);
    }

}
