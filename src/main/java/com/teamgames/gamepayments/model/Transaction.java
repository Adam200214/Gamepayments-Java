package com.teamgames.gamepayments.model;

import lombok.Data;

/**
 * @author Nelson Sanchez
 */
@Data
public class Transaction {
	public final String productId, name, username, paymentType, invoice, paymentStatus, deliveryStatus;
	public final float price, priceWithDiscount, tax;
	public final int quantity, allowReclaim, gameServer;

	//TODO: consider refining data model / usage of DTO's / Kotlin
}
