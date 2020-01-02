package com.teamgames.gamepayments.configuration;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Created by Jason MK on 2020-01-02 at 12:15 p.m.
 */

public class Configuration {

    private final String version;

    private final String address;

    private final boolean local;

    private final String localAddress;

    @Inject
    public Configuration(
            @Named("gamepayments.version") String version,
            @Named("gamepayments.address") String address,
            @Named("gamepayments.local.enabled") boolean local,
            @Named("gamepayments.local.address") String localAddress) {
        this.version = version;
        this.address = address;
        this.local = local;
        this.localAddress = localAddress;
    }

    public String getVersion() {
        return version;
    }

    public String getAddress() {
        return address;
    }

    public boolean isLocal() {
        return local;
    }

    public String getLocalAddress() {
        return localAddress;
    }
}
