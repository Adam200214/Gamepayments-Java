package com.teamgames;

import com.google.common.collect.ImmutableList;
import com.teamgames.gamepayments.response.AbstractAPIResponse;
import com.teamgames.gamepayments.service.PlayerStoreService;
import com.teamgames.gamepayments.service.PlayerStoreService.ConfirmUsernameDTO;
import com.teamgames.gamepayments.service.PlayerStoreService.SellProductDTO;
import com.teamgames.gamepayments.service.TransactionService;
import com.teamgames.gamepayments.service.TransactionService.ClaimPurchasesDTO;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.PrintStream;
import java.util.List;

public class ApplicationEntryPoint {

    public static void main(String[] args) {
        try (GamePayments payments = GamePayments.fromProperties(null)) {
            final PlayerStoreService store = payments.getStore();
            final TransactionService transactions = payments.getTransactions();

            final Player player = new LoggingPlayer(System.out);

            final ConfirmUsernameDTO confirm = ConfirmUsernameDTO.builder().username("test").verificationKey("test").build();
            final SellProductDTO sell = SellProductDTO.builder().price(123).productId(123).productName("test").quantity(123).username("test").build();
            final ClaimPurchasesDTO claim = ClaimPurchasesDTO.builder().username("test").build();

            //asynchronous requests
            System.out.println("Beginning asynchronous requests");

            store.confirmUsername(confirm, new ResultSubscriberAdapter<>(player));
            store.sellProduct(sell, new ResultSubscriberAdapter<>(player));
            transactions.claimPurchases(claim, new ResultSubscriberAdapter<>(player));

            //synchronous requests
            System.out.println("Beginning synchronous requests");

            final List<AbstractAPIResponse> responses = ImmutableList.of(
                    store.confirmUsername(confirm),
                    store.sellProduct(sell),
                    transactions.claimPurchases(claim)
            );

            responses.forEach(System.out::println);
        }
    }

    private static class ResultSubscriberAdapter<T> implements Subscriber<T> {

        private final Player player;

        ResultSubscriberAdapter(Player player) {
            this.player = player;
        }

        @Override
        public void onSubscribe(Subscription subscription) {
            subscription.request(1L);
        }

        @Override
        public void onNext(T result) {
            player.sendMessage("onNext(" + result + ")");
        }

        @Override
        public void onError(Throwable exception) {
            player.sendMessage("onError(" + exception + ")");
        }

        @Override
        public void onComplete() {
            player.sendMessage("onComplete()");
        }
    }

    interface Player {
        void sendMessage(String message);
        void addItem(int id, int quantity);
    }

    @RequiredArgsConstructor
    private static class LoggingPlayer implements Player {

        private final PrintStream out;

        @Override
        public void sendMessage(String message) {
            out.println(message);
        }

        @Override
        public void addItem(int id, int quantity) {
            sendMessage("Adding " + quantity + " of item with id " + id);
        }
    }
}
