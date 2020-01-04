package com.teamgames.gamepayments.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.teamgames.gamepayments.response.TransactionResponse;
import lombok.Builder;
import lombok.Data;
import org.reactivestreams.Subscriber;

import java.util.Objects;

/**
 * Created by Jason MK on 2020-01-02 at 1:11 p.m.
 */
@Singleton
public class TransactionService {

    private static final String CLAIM_PURCHASE_ENDPOINT = "/api/v1/client/global/claim-purchase";

    private final HttpService http;

    @Inject
    public TransactionService(HttpService http) {
        this.http = Objects.requireNonNull(http);
    }

    public void claimPurchases(ClaimPurchasesDTO request, Subscriber<? super TransactionResponse> subscriber) {
        http.post(TransactionResponse.class, request, CLAIM_PURCHASE_ENDPOINT).subscribe(subscriber);
    }

    public TransactionResponse claimPurchasesBlocking(ClaimPurchasesDTO request) {
        return http.postBlocking(TransactionResponse.class, request, CLAIM_PURCHASE_ENDPOINT);
    }

    @Data
    @Builder
    public static class ClaimPurchasesDTO {
        private final String username;
    }
}
