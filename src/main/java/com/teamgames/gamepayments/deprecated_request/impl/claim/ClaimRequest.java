package com.teamgames.gamepayments.deprecated_request.impl.claim;

import com.teamgames.gamepayments.deprecated_request.Request;
import com.teamgames.gamepayments.deprecated_request.result.impl.ClaimRequestResult;

/**
 * Created by Jason MK on 2020-01-02 at 2:41 p.m.
 */
public class ClaimRequest implements Request<ClaimRequestResult> {

    private final String apiKey;

    private final String username;

    public ClaimRequest(String apiKey, String username) {
        this.apiKey = apiKey;
        this.username = username;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public ClaimRequestResult call() throws Exception {
        return null;
    }
}
