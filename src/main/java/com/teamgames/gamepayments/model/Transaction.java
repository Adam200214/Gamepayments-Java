package com.teamgames.gamepayments.model;

/**
 * @author Nelson Sanchez
 */

public class Transaction {

	public final String productId;

	public final String name;

	public final float price;

	public final float priceWithDiscount;

	public final int quantity;

	public final int allowReclaim;

	public final int gameServer;

	public final String username;

	public final String paymentType;

	public final float tax;

	public final String invoice;

	public final String paymentStatus;

	public final String deliveryStatus;

	public Transaction(String productId, String name, float price, float priceWithDiscount, int quantity, int allowReclaim, int gameServer, String username, String paymentType, float tax, String invoice, String paymentStatus, String deliveryStatus) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.priceWithDiscount = priceWithDiscount;
		this.quantity = quantity;
		this.allowReclaim = allowReclaim;
		this.gameServer = gameServer;
		this.username = username;
		this.paymentType = paymentType;
		this.tax = tax;
		this.invoice = invoice;
		this.paymentStatus = paymentStatus;
		this.deliveryStatus = deliveryStatus;
	}

	public String getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	public float getPriceWithDiscount() {
		return priceWithDiscount;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getAllowReclaim() {
		return allowReclaim;
	}

	public int getGameServer() {
		return gameServer;
	}

	public String getUsername() {
		return username;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public float getTax() {
		return tax;
	}

	public String getInvoice() {
		return invoice;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}
}
