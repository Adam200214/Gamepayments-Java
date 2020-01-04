package com.teamgames.gamepayments.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.teamgames.GamePayments;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by Jason MK on 2020-01-02 at 12:35 p.m.
 */
public class ConfigurationModule extends AbstractModule {

    private static final String PROPERTIES_RESOURCE = "application.properties";

    private final String resource;

    public ConfigurationModule(String resource) {
        this.resource = Objects.requireNonNull(resource);
    }

    public ConfigurationModule() {
        this(PROPERTIES_RESOURCE);
    }

    @Override
    protected void configure() {
        try (InputStream stream = GamePayments.class.getProtectionDomain().getClassLoader().getResourceAsStream(resource)) {
            Properties properties = new Properties();

            properties.load(stream);

            System.getProperties().load(stream);

            Names.bindProperties(binder(), properties);
        } catch (IOException ioe) {
            throw new RuntimeException("Unable to configure application properties.", ioe);
        }
    }
}
