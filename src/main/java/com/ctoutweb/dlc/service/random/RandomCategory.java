package com.ctoutweb.dlc.service.random;

import java.util.Arrays;
import java.util.Optional;

public enum RandomCategory {
	REGISTEREMAILTOKEN(1),
	RESETPASSWORDTOKEN(2),
	URLTOKEN(3),
	ACTIVATEACCOUNTTOKEN(4);
	
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
