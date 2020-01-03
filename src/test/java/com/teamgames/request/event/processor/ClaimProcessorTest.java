package com.teamgames.request.event.processor;

import com.teamgames.GamePayments;
import com.teamgames.gamepayments.request.event.RequestEvent;
import com.teamgames.gamepayments.request.event.RequestEventResult;
import com.teamgames.gamepayments.request.event.processor.impl.ClaimProcessor;
import com.teamgames.gamepayments.request.impl.claim.ClaimRequest;
import com.teamgames.gamepayments.request.result.impl.ClaimRequestResult;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jason MK on 2020-01-03 at 10:51 a.m.
 */
@RunWith(JUnit4.class)
public class ClaimProcessorTest {

    private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    private GamePayments gamePayments;

    private String apiKey;

    private RequestEvent<ClaimRequestResult, ClaimRequest> request;

    @Before
    public void init() throws IOException {
        try (InputStream input = ClaimProcessorTest.class.getProtectionDomain().getClassLoader().getResourceAsStream("apikey.properties")) {
            Properties properties = new Properties();

            properties.load(input);

            apiKey = properties.getProperty("gamepayments.apikey");
        }
        gamePayments = new GamePayments.Builder(apiKey).build();

        request = new RequestEvent<>(new ClaimRequest(apiKey, "Test"), Duration.ofSeconds(10));

        service.scheduleAtFixedRate(() -> gamePayments.getClaimService().run(), 0, 600, TimeUnit.MILLISECONDS);
    }

    @Test
    public void assertOk() throws InterruptedException {
        service.schedule(() -> gamePayments.getClaimService().submit(request), 1, TimeUnit.SECONDS);

        while (request.getEventResult() == RequestEventResult.NONE) {
            Thread.sleep(200L);
        }
        Assert.assertSame(RequestEventResult.OK, request.getEventResult());
    }

    @After
    public void shutdown() {
        service.shutdown();
    }
}
