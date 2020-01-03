package com.teamgames;

import com.teamgames.gamepayments.GamePayments;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by Jason MK on 2020-01-02 at 2:02 p.m.
 */
@RunWith(JUnit4.class)
public class GamePaymentsBuilderTest {

    @Test
    public void assertNonNull() {
        GamePayments payments = new GamePayments.Builder("test").build();

        Assert.assertNotNull(payments);
        Assert.assertNotNull(payments.getStoreService());
        Assert.assertNotNull(payments.getClaimService());
    }
}
