package com.teamgames;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.teamgames.gamepayments.module.ConfigurationModule;
import com.teamgames.gamepayments.module.HttpClientModule;
import com.teamgames.gamepayments.service.HttpClientService;
import com.teamgames.gamepayments.service.PlayerStoreService;
import com.teamgames.gamepayments.service.ClaimService;

public class GamePayments {

	private final PlayerStoreService storeService;

	private final ClaimService claimService;

	private final HttpClientService httpClientService;

	private final String apiKey;

	private GamePayments(Builder builder) {
		this.storeService = builder.storeService;
		this.claimService = builder.claimService;
		this.httpClientService = builder.httpClientService;
		this.apiKey = builder.apiKey;
	}

	public PlayerStoreService getStoreService() {
		return storeService;
	}

	public ClaimService getClaimService() {
		return claimService;
	}

	public HttpClientService getHttpClientService() {
		return httpClientService;
	}

	public String getApiKey() {
		return apiKey;
	}

	public static class Builder {

		private final PlayerStoreService storeService;

		private final ClaimService claimService;

		private final HttpClientService httpClientService;

		private final String apiKey;

		public Builder(String apiKey) {
			Injector injector = Guice.createInjector(new ConfigurationModule(), new HttpClientModule());

			this.apiKey = apiKey;
			this.storeService = injector.getInstance(PlayerStoreService.class);
			this.claimService = injector.getInstance(ClaimService.class);
			this.httpClientService = injector.getInstance(HttpClientService.class);
		}

		public GamePayments build() {
			return new GamePayments(this);
		}

	}

}
