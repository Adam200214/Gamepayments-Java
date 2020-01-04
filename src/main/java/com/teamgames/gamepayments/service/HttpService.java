package com.teamgames.gamepayments.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.teamgames.gamepayments.configuration.Configuration;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.RxHttpClient;
import lombok.Getter;
import org.reactivestreams.Publisher;

import java.io.Closeable;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Base64;

@Getter
@Singleton
public class HttpService implements Closeable {

    private static final String DEFAULT_USER_AGENT = "GamepaymentsJavaClientAPI/%s";

    private final Configuration configuration;
    private final HttpClient http;
    private final String authorization, agent;

    @Inject
    public HttpService(ConfigurationService service) throws MalformedURLException {
        this.configuration = service.getConfiguration();
        this.http = RxHttpClient.create(new URL(configuration.getAPIEndpoint()));
        this.authorization = Base64.getEncoder().encodeToString(configuration.getKey().getBytes(Charset.defaultCharset()));
        this.agent = String.format(DEFAULT_USER_AGENT, configuration.getVersion());
    }

    public <R, P> Publisher<R> postAsync(Class<R> type, P payload, String endpoint) {
        final MutableHttpRequest<P> request = initialise(HttpRequest.POST(endpoint, payload));
        return http.retrieve(request, type);
    }

    public <R, P> R post(Class<R> type, P payload, String endpoint) {
        final MutableHttpRequest<P> request = initialise(HttpRequest.POST(endpoint, payload));
        return http.toBlocking().retrieve(request, type);
    }

    private <T> MutableHttpRequest<T> initialise(MutableHttpRequest<T> request) {
        return request.header(HttpHeaders.AUTHORIZATION, authorization).header(HttpHeaders.USER_AGENT, agent);
    }

    @Override
    public void close() {
        this.http.close();
    }
}
