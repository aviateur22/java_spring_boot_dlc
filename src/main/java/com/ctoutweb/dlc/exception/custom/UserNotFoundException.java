package com.ctoutweb.dlc.exception.custom;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException (String message) {
		super(message);
	}
}
