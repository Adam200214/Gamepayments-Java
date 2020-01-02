package com.teamgames;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.teamgames.gamepayments.service.PlayerStoreService;
import com.teamgames.gamepayments.module.ConfigurationModule;
import com.teamgames.gamepayments.service.TransactionService;

public class GamePayments {

	private final PlayerStoreService store;

	private final TransactionService transactions;

	private GamePayments(Builder builder) {
		this.store = builder.store;
		this.transactions = builder.transactions;
	}

	public PlayerStoreService getStore() {
		return store;
	}

	public TransactionService getTransactions() {
		return transactions;
	}

	public static class Builder {

		private final PlayerStoreService store;

		private final TransactionService transactions;

		private final String apiKey;

		public Builder(String apiKey) {
			this.apiKey = apiKey;

			Injector injector = Guice.createInjector(new ConfigurationModule());

			store = injector.getInstance(PlayerStoreService.class);

			transactions = injector.getInstance(TransactionService.class);
		}

		public GamePayments build() {

			return new GamePayments(this);
		}

	}

}
