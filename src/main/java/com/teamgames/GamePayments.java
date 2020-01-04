package com.teamgames;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.teamgames.gamepayments.module.ConfigurationModule;
import com.teamgames.gamepayments.service.HttpService;
import com.teamgames.gamepayments.service.PlayerStoreService;
import com.teamgames.gamepayments.service.TransactionService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GamePayments implements AutoCloseable {

	private final HttpService http;
	private final PlayerStoreService store;
	private final TransactionService transactions;

	public static GamePayments create(String properties) {
		Injector injector = Guice.createInjector(properties == null ? new ConfigurationModule() : new ConfigurationModule(properties));
		HttpService http = injector.getInstance(HttpService.class);
		PlayerStoreService store = injector.getInstance(PlayerStoreService.class);
		TransactionService transactions = injector.getInstance(TransactionService.class);
		return new GamePayments(http, store, transactions);
	}

	@Override
	public void close() {
		http.close();
	}
}
