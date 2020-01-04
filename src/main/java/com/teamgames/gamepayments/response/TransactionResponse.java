package com.teamgames.gamepayments.response;

import com.teamgames.gamepayments.model.Transaction;
import lombok.Getter;

import java.util.List;

/**
 * @author Nelson Sanchez
 */
@Getter
public class TransactionResponse extends AbstractAPIResponse {
	
	private final List<Transaction> transactions;

	public TransactionResponse(String message, String extendedMessage, List<Transaction> transactions) {
		super(message, extendedMessage);
		this.transactions = transactions;
	}
}
