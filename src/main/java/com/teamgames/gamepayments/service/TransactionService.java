package com.teamgames.gamepayments.service;

import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.teamgames.gamepayments.Configurations;
import com.teamgames.gamepayments.TransactionResponse;
import com.teamgames.request.Connection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason MK on 2020-01-02 at 1:11 p.m.
 */
public class TransactionService {

    private final ConfigurationService configurationService;

    @Inject
    public TransactionService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public TransactionResponse getResponse(String apiKey, String username) throws Exception {
        Map<String, Object> params = new HashMap<>();

        params.put("username", username);

        final String ADDRESS = Configurations.isLocal ? Configurations.LOCAL_ADDRESS
                : Configurations.GAMEPAYMENTS_ADDRESS;

        final String serverResponse = Connection.sendPostParams(params,
                ADDRESS + "/api/v1/client/global/claim-purchase", apiKey);

        return new GsonBuilder().create().fromJson(serverResponse, TransactionResponse.class);

    }
}
