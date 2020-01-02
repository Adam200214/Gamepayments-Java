package com.teamgames.gamepayments;

import com.teamgames.gamepayments.model.Transaction;

/**
 * @author Nelson Sanchez
 */

public class TransactionResponse {
	
	private final String message;

	private final String extendedMessage;

	private final Transaction[] transactions;

	public TransactionResponse(String message, String extendedMessage, Transaction[] transactions) {
		this.message = message;
		this.extendedMessage = extendedMessage;
		this.transactions = transactions;
	}

	public Transaction[] getTransactions() {
		return transactions;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getExtendedMessage() {
		return extendedMessage;
	}

}
