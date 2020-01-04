package com.teamgames.gamepayments.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.teamgames.gamepayments.response.PlayerStoreResponse;
import lombok.Builder;
import lombok.Data;
import org.reactivestreams.Subscriber;

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

	public void confirmUsername(ConfirmUsernameDTO request, Subscriber<? super PlayerStoreResponse> subscriber) {
		http.post(PlayerStoreResponse.class, request, CONFIRM_USERNAME_ENDPOINT).subscribe(subscriber);
	}

	public PlayerStoreResponse confirmUsernameBlocking(ConfirmUsernameDTO request) {
		return http.postBlocking(PlayerStoreResponse.class, request, CONFIRM_USERNAME_ENDPOINT);
	}

	public void sellProduct(SellProductDTO request, Subscriber<? super PlayerStoreResponse> subscriber) {
		http.post(PlayerStoreResponse.class, request, SELL_PRODUCT_ENDPOINT).subscribe(subscriber);
	}

	public PlayerStoreResponse sellProductBlocking(SellProductDTO request) {
		return http.postBlocking(PlayerStoreResponse.class, request, SELL_PRODUCT_ENDPOINT);
	}

	@Data
	@Builder
	public static class ConfirmUsernameDTO {
		private final String username, verificationKey;
	}

	@Data
	@Builder
	public static class SellProductDTO {
		private final String username, productName;
		private final int productId, quantity;
		private final double price;
	}
}
