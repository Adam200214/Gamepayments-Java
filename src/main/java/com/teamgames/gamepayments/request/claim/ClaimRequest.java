package com.teamgames.gamepayments.request.claim;

import com.teamgames.gamepayments.request.Request;

/**
 * Created by Jason MK on 2020-01-02 at 2:41 p.m.
 */
public class ClaimRequest implements Request {

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
}
