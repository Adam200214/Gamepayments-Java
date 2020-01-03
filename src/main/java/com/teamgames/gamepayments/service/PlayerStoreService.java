package com.teamgames.gamepayments.service;

import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.teamgames.gamepayments.PlayerStoreResponse;
import com.teamgames.request.Connection;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerStoreService {

	private final ConfigurationService configurationService;

	@Inject
	public PlayerStoreService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public PlayerStoreResponse confirmUsername(String apiKey, String username, String verificationKey) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("username", username);

		params.put("verificationKey", verificationKey);

		final String address = configurationService.getConfiguration().isLocal() ? configurationService.getConfiguration().getLocalAddress()
				: configurationService.getConfiguration().getAddress();

		final String serverResponse = Connection.sendPostParams(params, address + "/api/v1/client/global/confirm-player-seller", apiKey);

		return new GsonBuilder().create().fromJson(serverResponse, PlayerStoreResponse.class);
	}

	public PlayerStoreResponse sellProduct(String apiKey, String username, int productId, String productName, double price, int quantity) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("username", username);
		params.put("productId", productId);
		params.put("productName", productName);
		params.put("price", price);
		params.put("quantity", quantity);

		final String address = configurationService.getConfiguration().isLocal() ? configurationService.getConfiguration().getLocalAddress()
				: configurationService.getConfiguration().getAddress();

		final String serverResponse = Connection.sendPostParams(params,
				address + "/api/v1/client/global/sell-player-product", apiKey);

		return new GsonBuilder().create().fromJson(serverResponse, PlayerStoreResponse.class);

	}

}
