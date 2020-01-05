package com.teamgames;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.teamgames.gamepayments.configuration.Configuration;
import com.teamgames.gamepayments.module.ConfigurationModule;
import com.teamgames.gamepayments.service.ConfigurationService;
import com.teamgames.gamepayments.service.HttpService;
import com.teamgames.gamepayments.service.PlayerStoreService;
import com.teamgames.gamepayments.service.TransactionService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.MalformedURLException;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GamePayments implements AutoCloseable {

	private final HttpService http;
	private final PlayerStoreService store;
	private final TransactionService transactions;

	public static GamePayments fromProperties(@Nullable String properties) {
		Injector injector = Guice.createInjector(properties == null ? new ConfigurationModule() : new ConfigurationModule(properties));
		HttpService http = injector.getInstance(HttpService.class);
		PlayerStoreService store = injector.getInstance(PlayerStoreService.class);
		TransactionService transactions = injector.getInstance(TransactionService.class);
		return new GamePayments(http, store, transactions);
	}

	public static GamePayments fromConfiguration(@Nonnull Configuration configuration) throws MalformedURLException {
		ConfigurationService config = new ConfigurationService(configuration);
		HttpService http = new HttpService(config);
		PlayerStoreService store = new PlayerStoreService(http);
		TransactionService transactions = new TransactionService(http);
		return new GamePayments(http, store, transactions);
	}

	@Override
	public void close() {
		http.close();
	}
}
