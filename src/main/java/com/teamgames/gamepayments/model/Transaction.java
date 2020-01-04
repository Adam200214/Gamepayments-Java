package com.teamgames.gamepayments.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nelson Sanchez
 */
@Data
@NoArgsConstructor
public class Transaction {

	private String productId, name, username, paymentType, invoice, paymentStatus, deliveryStatus;
	private float price, priceWithDiscount, tax;
	private int quantity, allowReclaim, gameServer;

}
