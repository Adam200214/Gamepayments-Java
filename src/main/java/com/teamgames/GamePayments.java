package com.teamgames;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.teamgames.gamepayments.module.ConfigurationModule;
import com.teamgames.gamepayments.module.HttpClientModule;
import com.teamgames.gamepayments.service.PlayerStoreService;
import com.teamgames.gamepayments.service.TransactionService;
import org.apache.http.client.fluent.Executor;

public class GamePayments {

	private final PlayerStoreService store;

	private final TransactionService transactions;

	private final Executor httpClient;

	private GamePayments(Builder builder) {
		this.store = builder.store;
		this.transactions = builder.transactions;
		this.httpClient = builder.httpClient;
	}

	public PlayerStoreService getStore() {
		return store;
	}

	public TransactionService getTransactions() {
		return transactions;
	}

	public Executor getHttpClient() {
		return httpClient;
	}

	public static class Builder {

		private final PlayerStoreService store;

		private final TransactionService transactions;

		private final Executor httpClient;

		private final String apiKey;

		public Builder(String apiKey) {
			Injector injector = Guice.createInjector(new ConfigurationModule(), new HttpClientModule());

			this.apiKey = apiKey;
			this.store = injector.getInstance(PlayerStoreService.class);
			this.transactions = injector.getInstance(TransactionService.class);
			this.httpClient = injector.getInstance(Executor.class);
		}

		public GamePayments build() {
			return new GamePayments(this);
		}

	}

}
