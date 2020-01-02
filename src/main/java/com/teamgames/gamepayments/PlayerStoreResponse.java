package com.teamgames.gamepayments;

public class PlayerStoreResponse {
	
	private final String message;

	private final String extendedMessage;

	public PlayerStoreResponse(String message, String extendedMessage) {
		this.message = message;
		this.extendedMessage = extendedMessage;
	}

	public String getMessage() {
		return message;
	}
	
	public String getExtendedMessage() {
		return extendedMessage;
	}

}
