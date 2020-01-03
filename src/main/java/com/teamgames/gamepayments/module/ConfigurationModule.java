package com.teamgames.gamepayments.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.teamgames.gamepayments.GamePayments;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Jason MK on 2020-01-02 at 12:35 p.m.
 */
public class ConfigurationModule extends AbstractModule {

    private static final String PROPERTIES_RESOURCE = "application.properties";

    @Override
    protected void configure() {
        try (InputStream stream = GamePayments.class.getProtectionDomain().getClassLoader().getResourceAsStream(PROPERTIES_RESOURCE)) {
            Properties properties = new Properties();

            properties.load(stream);

            System.getProperties().load(stream);

            Names.bindProperties(binder(), properties);
        } catch (IOException ioe) {
            throw new RuntimeException("Unable to configure application properties.", ioe);
        }
    }
}
