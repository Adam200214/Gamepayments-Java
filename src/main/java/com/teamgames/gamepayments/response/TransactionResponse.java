package com.teamgames.gamepayments.response;

import com.teamgames.gamepayments.model.Transaction;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Nelson Sanchez
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TransactionResponse extends AbstractAPIResponse {
	
	private List<Transaction> transactions;

}
