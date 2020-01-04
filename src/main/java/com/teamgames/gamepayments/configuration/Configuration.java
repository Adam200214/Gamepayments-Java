package com.teamgames.gamepayments.configuration;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import lombok.Getter;

import java.util.Objects;

/**
 * Created by Jason MK on 2020-01-02 at 12:15 p.m.
 */
@Getter
public class Configuration {

    private final String key, version, address, localAddress;
    private final boolean local;

    @Inject
    public Configuration(
            @Named("gamepayments.api.key") String key,
            @Named("gamepayments.version") String version,
            @Named("gamepayments.address") String address,
            @Named("gamepayments.local.enabled") boolean local,
            @Named("gamepayments.local.address") String localAddress) {
        this.key = Objects.requireNonNull(key);
        this.version = Objects.requireNonNull(version);
        this.address = Objects.requireNonNull(address);
        this.local = local;
        this.localAddress = Objects.requireNonNull(localAddress);
    }

    public String getAPIEndpoint() {
        return local ? localAddress : address;
    }
}
