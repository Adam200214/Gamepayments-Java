package com.teamgames.gamepayments.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.apache.http.client.fluent.Executor;

/**
 * Created by Jason MK on 2020-01-02 at 3:45 p.m.
 */
public class HttpClientModule extends AbstractModule {

    @Provides
    public Executor provide() {
        return Executor.newInstance();
    }

}
