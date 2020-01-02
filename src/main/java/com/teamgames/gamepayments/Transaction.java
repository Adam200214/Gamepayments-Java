package com.teamgames.gamepayments;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;

import com.teamgames.request.Connection;

/**
 * @author Nelson Sanchez
 */

public class Transaction {

	public String productId;

	public String name;

	public float price;

	public float priceWithDiscount;

	public int quantity;

	public int allowReclaim;

	public int gameServer;

	public String username;

	public String paymentType;

	public float tax;

	public String invoice;

	public String paymentStatus;

	public String deliveryStatus;

	public static ArrayList<String> orders = new ArrayList<String>();

	public static void main(String args[]) throws Exception {
		
	}

}
