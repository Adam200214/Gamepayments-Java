package com.teamgames.gamepayments.service;

import com.google.inject.Inject;
import com.teamgames.gamepayments.configuration.Configuration;

/**
 * Created by Jason MK on 2020-01-02 at 12:18 p.m.
 */
public class ConfigurationService {

    private final Configuration configuration;

    @Inject
    public ConfigurationService(Configuration configuration) {
        this.configuration = configuration;
    }

    public String getAddress() {
        return configuration.isLocal() ? configuration.getLocalAddress() : configuration.getAddress();
    }

    public boolean isLocal() {
        return configuration.isLocal();
    }

    public String getVersion() {
        return configuration.getVersion();
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
