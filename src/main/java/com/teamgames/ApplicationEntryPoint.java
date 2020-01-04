package com.teamgames;

import com.google.common.collect.ImmutableList;
import com.teamgames.gamepayments.response.AbstractAPIResponse;
import com.teamgames.gamepayments.service.PlayerStoreService;
import com.teamgames.gamepayments.service.PlayerStoreService.ConfirmUsernameDTO;
import com.teamgames.gamepayments.service.PlayerStoreService.SellProductDTO;
import com.teamgames.gamepayments.service.TransactionService;
import com.teamgames.gamepayments.service.TransactionService.ClaimPurchasesDTO;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

public class ApplicationEntryPoint {

    public static void main(String[] args) {
        try (GamePayments payments = GamePayments.create(null)) {
            final PlayerStoreService store = payments.getStore();
            final TransactionService transactions = payments.getTransactions();

            final Subscriber<Object> subscriber = new SimpleSubscriberAdapter<>();

            final ConfirmUsernameDTO confirm = ConfirmUsernameDTO.builder().username("test").verificationKey("test").build();
            final SellProductDTO sell = SellProductDTO.builder().price(123).productId(123).productName("test").quantity(123).username("test").build();
            final ClaimPurchasesDTO claim = ClaimPurchasesDTO.builder().username("test").build();

            //asynchronous requests
            System.out.println("Beginning asynchronous requests");

            store.confirmUsername(confirm, subscriber);
            store.sellProduct(sell, subscriber);
            transactions.claimPurchases(claim, subscriber);

            //synchronous requests
            System.out.println("Beginning synchronous requests");

            final List<AbstractAPIResponse> responses = ImmutableList.of(
                    store.confirmUsernameBlocking(confirm),
                    store.sellProductBlocking(sell),
                    transactions.claimPurchasesBlocking(claim)
            );

            responses.forEach(System.out::println);
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
