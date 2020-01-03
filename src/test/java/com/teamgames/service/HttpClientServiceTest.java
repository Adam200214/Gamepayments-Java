package com.teamgames.service;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.teamgames.GamePayments;
import com.teamgames.gamepayments.TransactionResponse;
import com.teamgames.gamepayments.module.ConfigurationModule;
import com.teamgames.gamepayments.module.HttpClientModule;
import com.teamgames.gamepayments.service.ConfigurationService;
import com.teamgames.gamepayments.service.HttpClientService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.client.SystemDefaultCredentialsProvider;
import org.apache.http.ssl.SSLContexts;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jason MK on 2020-01-02 at 9:01 p.m.
 */
@RunWith(JUnit4.class)
public class HttpClientServiceTest {

    @Test
    public void assertFluentAPI() throws URISyntaxException, IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        Injector injector = Guice.createInjector(new ConfigurationModule());

        ConfigurationService configurationService = injector.getInstance(ConfigurationService.class);

        Executor executor = Executor.newInstance()
                .clearAuth()
                .clearCookies();

        URIBuilder builder = new URIBuilder()
                .setScheme("https")
                .setHost("api.gamepayments.net")
                .setPath("/api/v1/client/global/claim-purchase")
                .setPort(443)
                .addParameter("username", "test");

        Request request = Request.Post(builder.build())
                .setHeader("Authorization", "OVB5V1lsanBxQVlsQUJWMmZIUkdrbVVnTFRTVVhJZE9yY1lJMFdSSmxpTm9LUGIzVUxXdVQxSDhSWDY5Qk9rMlc2aTJ6YXJo")
                .setHeader("Content-Type", ContentType.APPLICATION_FORM_URLENCODED.getMimeType())
                .connectTimeout(10_000)
                .socketTimeout(10_000);

        Response response = executor.execute(request);

        TransactionResponse transactionResponse = new GsonBuilder().create().fromJson(response.returnContent().asString(), TransactionResponse.class);

        System.out.println("Response: " + ReflectionToStringBuilder.toString(transactionResponse, ToStringStyle.JSON_STYLE));
    }
}
