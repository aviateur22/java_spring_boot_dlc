package com.ctoutweb.dlc.service.random;

import java.util.Arrays;
import java.util.Optional;

public enum RandomCategory {
	REGISTER(1),
	RESETPASSWORD(2),
	EMAILCONFIRMATION(3);
	
	private int index;
	
	private RandomCategory(int index) {
		this.index = index;
	}
	
	public static Optional<RandomCategory> getCategoryFromIndex(int index) {
        return Arrays.stream(values())
            .filter(cat -> cat.index == index)
            .findFirst();
    }

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

}
