package com.teamgames.gamepayments.service;

import com.google.inject.Inject;
import com.teamgames.gamepayments.response.TransactionResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason MK on 2020-01-02 at 1:11 p.m.
 */
public class TransactionService {

    private static final String CLAIM_PURCHASE_ENDPOINT = "/api/v1/client/global/claim-purchase";

    private final HttpService http;

    @Inject
    public TransactionService(HttpService httpService) {
        this.http = httpService;
    }

    public TransactionResponse claimPurchases(String username) throws Exception {
        Map<String, Object> params = new HashMap<>();

        params.put("username", username);

        return http.post(TransactionResponse.class, params, CLAIM_PURCHASE_ENDPOINT);
    }
}
