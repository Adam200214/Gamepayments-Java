package com.teamgames.gamepayments.request.impl.claim;

import com.google.gson.GsonBuilder;
import com.teamgames.gamepayments.model.TransactionResponse;
import com.teamgames.gamepayments.request.Request;
import com.teamgames.gamepayments.request.result.impl.ClaimRequestResult;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.utils.URIBuilder;

/**
 * Created by Jason MK on 2020-01-02 at 2:41 p.m.
 */
public class ClaimRequest implements Request<ClaimRequestResult> {

    private final String apiKey;

    private final String username;

    public ClaimRequest(String apiKey, String username) {
        this.apiKey = apiKey;
        this.username = username;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public ClaimRequestResult create(Executor httpClient) throws Exception {
        URIBuilder builder = new URIBuilder()
                .setScheme("https")
                .setHost("api.gamepayments.net")
                .setPath("/api/v1/client/global/claim-purchase")
                .setPort(443)
                .addParameter("username", username);

        org.apache.http.client.fluent.Request request = org.apache.http.client.fluent.Request.Post(builder.build())
                .setHeader("Authorization", Base64.encodeBase64String(apiKey.getBytes()))
                .connectTimeout(10_000)
                .socketTimeout(10_000);

        Response response = httpClient.execute(request);

        return new GsonBuilder().create().fromJson(response.returnContent().asString(), ClaimRequestResult.class);
    }
}
