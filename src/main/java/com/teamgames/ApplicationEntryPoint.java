package com.teamgames;

import com.google.common.collect.ImmutableList;
import com.teamgames.gamepayments.service.PlayerStoreService;
import com.teamgames.gamepayments.service.TransactionService;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class ApplicationEntryPoint {

    public static void main(String[] args) {
        try (GamePayments payments = GamePayments.create(null)) {
            final PlayerStoreService store = payments.getStore();
            final TransactionService transactions = payments.getTransactions();

            final Subscriber<Object> subscriber = new SimpleSubscriberAdapter<>();

            final PlayerStoreService.ConfirmUsernameDTO confirm = PlayerStoreService.ConfirmUsernameDTO.builder().username("test").verificationKey("test").build();
            final TransactionService.ClaimPurchasesDTO claim = TransactionService.ClaimPurchasesDTO.builder().username("test").build();

            //asynchronous requests
            System.out.println("Beginning asynchronous requests");

            store.confirmUsername(confirm, subscriber);
            transactions.claimPurchases(claim, subscriber);

            //synchronous requests
            System.out.println("Beginning synchronous requests");

            ImmutableList.of(store.confirmUsernameBlocking(confirm), transactions.claimPurchasesBlocking(claim)).forEach(System.out::println);
        }
    }

    private static class SimpleSubscriberAdapter<T> implements Subscriber<T> {

        @Override
        public void onSubscribe(Subscription subscription) {
            subscription.request(1L);
        }

        @Override
        public void onNext(T element) {
            System.out.println("onNext(" + element + ")");
        }

        @Override
        public void onError(Throwable exception) {
            System.out.println("onError(" + exception + ")");
        }

        @Override
        public void onComplete() {
            System.out.println("onComplete()");
        }
    }
}
