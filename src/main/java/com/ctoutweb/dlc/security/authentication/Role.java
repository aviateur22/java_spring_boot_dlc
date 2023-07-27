package com.ctoutweb.dlc.security.authentication;

public enum Role {
	ADMIN(1), USER(2);
	
	private final int value;
	
	private Role(int value) {
	    this.value = value;
	}

	public int getValue() {
		return value;
	}
}
