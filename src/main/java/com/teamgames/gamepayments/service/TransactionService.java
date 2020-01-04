package com.teamgames.gamepayments.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.teamgames.gamepayments.response.TransactionResponse;
import lombok.Data;

/**
 * Created by Jason MK on 2020-01-02 at 1:11 p.m.
 */
@Singleton
public class TransactionService {

    private static final String CLAIM_PURCHASE_ENDPOINT = "/api/v1/client/global/claim-purchase";

    private final HttpService http;

    @Inject
    public TransactionService(HttpService http) {
        this.http = http;
    }

    public TransactionResponse claimPurchases(String username) {
        final ClaimPurchasesDTO request = new ClaimPurchasesDTO(username);
        return http.postBlocking(TransactionResponse.class, request, CLAIM_PURCHASE_ENDPOINT);
    }

    @Data
    private static class ClaimPurchasesDTO {
        private final String username;
    }
}
