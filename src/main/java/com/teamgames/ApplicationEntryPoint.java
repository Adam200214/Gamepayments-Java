package com.teamgames;

import com.google.common.collect.ImmutableList;
import com.teamgames.gamepayments.response.AbstractAPIResponse;
import com.teamgames.gamepayments.service.PlayerStoreService;
import com.teamgames.gamepayments.service.TransactionService;

import java.util.List;

public class ApplicationEntryPoint {

    public static void main(String[] args) {
        try (GamePayments payments = GamePayments.create(null)) {
            final PlayerStoreService store = payments.getStore();
            final TransactionService transactions = payments.getTransactions();

            final List<AbstractAPIResponse> responses = ImmutableList.of(
                    store.confirmUsername("test", "test"),
                    transactions.claimPurchases("test")
            );

            responses.forEach(System.out::println);
        }
    }
}
