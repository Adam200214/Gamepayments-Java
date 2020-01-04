package com.teamgames.gamepayments.service;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.teamgames.gamepayments.response.PlayerStoreResponse;

import java.util.Map;

public class PlayerStoreService {

	private static final String CONFIRM_USERNAME_ENDPOINT = "/api/v1/client/global/confirm-player-seller";
	private static final String SELL_PRODUCT_ENDPOINT = "/api/v1/client/global/sell-player-product";

	private final HttpService http;

	@Inject
	public PlayerStoreService(HttpService httpService) {
		this.http = httpService;
	}

	public PlayerStoreResponse confirmUsername(String username, String verificationKey) {
		final Map<String, String> params = ImmutableMap.of(
				"username", username,
				"verificationKey", verificationKey
		);

		return http.post(PlayerStoreResponse.class, params, CONFIRM_USERNAME_ENDPOINT);
	}

	public PlayerStoreResponse sellProduct(String username, int productId, String productName, double price, int quantity) {
		final Map<String, ?> params = ImmutableMap.of(
				"username", username,
				"productId", productId,
				"productName", productName,
				"price", price,
				"quantity", quantity
		);

		return http.post(PlayerStoreResponse.class, params, SELL_PRODUCT_ENDPOINT);
	}
}
