package com.teamgames.gamepayments.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.teamgames.gamepayments.response.PlayerStoreResponse;
import lombok.Data;

import java.util.Objects;

@Singleton
public class PlayerStoreService {

	private static final String CONFIRM_USERNAME_ENDPOINT = "/api/v1/client/global/confirm-player-seller";
	private static final String SELL_PRODUCT_ENDPOINT = "/api/v1/client/global/sell-player-product";

	private final HttpService http;

	@Inject
	public PlayerStoreService(HttpService http) {
		this.http = Objects.requireNonNull(http);
	}

	public PlayerStoreResponse confirmUsername(String username, String verificationKey) {
		final ConfirmUsernameDTO request = new ConfirmUsernameDTO(username, verificationKey);
		return http.postBlocking(PlayerStoreResponse.class, request, CONFIRM_USERNAME_ENDPOINT);
	}

	public PlayerStoreResponse sellProduct(String username, int productId, String productName, double price, int quantity) {
		final SellProductDTO request = new SellProductDTO(username, productName, productId, quantity, price);
		return http.postBlocking(PlayerStoreResponse.class, request, SELL_PRODUCT_ENDPOINT);
	}

	@Data
	private static class ConfirmUsernameDTO {
		private final String username, verificationKey;
	}

	@Data
	private static class SellProductDTO {
		private final String username, productName;
		private final int productId, quantity;
		private final double price;
	}
}
