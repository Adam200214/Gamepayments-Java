package com.teamgames.gamepayments.service;

import com.google.inject.Inject;
import org.apache.http.client.fluent.Executor;

/**
 * Created by Jason MK on 2020-01-02 at 3:45 p.m.
 */
public class HttpClientService {

    private final Executor httpClient;

    private final ConfigurationService configurationService;

    @Inject
    public HttpClientService(Executor httpClient, ConfigurationService configurationService) {
        this.httpClient = httpClient;
        this.configurationService = configurationService;
    }

    public void close() {
        Executor.closeIdleConnections();
    }

    public Executor getHttpClient() {
        return httpClient;
    }
}
