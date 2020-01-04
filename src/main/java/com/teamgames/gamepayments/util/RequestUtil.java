package com.teamgames.gamepayments.util;

import com.google.gson.Gson;
import com.teamgames.request.Connection;

import java.util.Map;

public class RequestUtil {

    public static <T> T getResponse(Class<T> type, Map<String, Object> params, String address, String endpoint, String apiKey) throws Exception {
        String response = Connection.sendPostParams(params, address + endpoint, apiKey);
        return new Gson().fromJson(response, type);
    }

    private RequestUtil() throws IllegalStateException {
        throw new IllegalStateException("Unable to instantiate RequestUtil");
    }
}
