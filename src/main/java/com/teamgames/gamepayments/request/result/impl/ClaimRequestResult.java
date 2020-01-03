package com.teamgames.gamepayments.request.result.impl;

import com.teamgames.gamepayments.model.Transaction;
import com.teamgames.gamepayments.request.result.Result;

import java.util.List;

/**
 * Created by Jason MK on 2020-01-02 at 2:49 p.m.
 */
public class ClaimRequestResult implements Result {

    private final String message;

    private final String extendedMessage;

    private final List<Transaction> transactions;

    public ClaimRequestResult(String message, String extendedMessage, List<Transaction> transactions) {
        this.message = message;
        this.extendedMessage = extendedMessage;
        this.transactions = transactions;
    }

    public String getMessage() {
        return message;
    }

    public String getExtendedMessage() {
        return extendedMessage;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
