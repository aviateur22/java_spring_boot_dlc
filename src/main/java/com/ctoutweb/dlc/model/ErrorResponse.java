package com.ctoutweb.dlc.model;

public class ErrorResponse {
	private String errorMessage;

	
	public ErrorResponse(String message) {
		super();
		this.errorMessage = message;
	}

	/**
	 * @return the message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param message the message to set
	 */
	public void setErrorMessage(String message) {
		this.errorMessage = message;
	}
	
}
