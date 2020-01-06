package com.teamgames.gamepayments.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.teamgames.gamepayments.configuration.Configuration;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.RxHttpClient;
import org.reactivestreams.Publisher;

import java.io.Closeable;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Base64;

@Singleton
public class HttpService implements Closeable {

    private static final String DEFAULT_USER_AGENT = "GamepaymentsJavaClientAPI/%s";

    private final HttpClient client;
    private final String authorization, agent;

    @Inject
    public HttpService(ConfigurationService service) throws MalformedURLException {
        Configuration configuration = service.getConfiguration();
        URL endpoint = new URL(configuration.getAPIEndpoint());
        this.client = RxHttpClient.create(endpoint);
        this.authorization = Base64.getEncoder().encodeToString(configuration.getKey().getBytes(Charset.defaultCharset()));
        this.agent = String.format(DEFAULT_USER_AGENT, configuration.getVersion());
    }

    public <R, P> Publisher<R> async(Class<R> type, P payload, String endpoint) {
        final MutableHttpRequest<P> request = initialise(HttpRequest.POST(endpoint, payload));
        return client.retrieve(request, type);
    }

    public <R, P> R sync(Class<R> type, P payload, String endpoint) {
        final MutableHttpRequest<P> request = initialise(HttpRequest.POST(endpoint, payload));
        return client.toBlocking().retrieve(request, type);
    }

    private <T> MutableHttpRequest<T> initialise(MutableHttpRequest<T> request) {
        return request.header(HttpHeaders.AUTHORIZATION, authorization).header(HttpHeaders.USER_AGENT, agent);
    }

    @Override
    public void close() {
        client.close();
    }
}
