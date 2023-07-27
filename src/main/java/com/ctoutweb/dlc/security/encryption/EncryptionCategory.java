package com.ctoutweb.dlc.security.encryption;

import java.util.Arrays;
import java.util.Optional;

public enum EncryptionCategory {
	REGISTEREMAIL(1),
	RESETPASSWORD(2),
	RANDOMWORD(3);
	
	private final int categoryValue;
	
	private EncryptionCategory(int category) {
		this.categoryValue = category;
	}
	
	public static Optional<EncryptionCategory> getSubjectFromValue(int value) {
        return Arrays.stream(values())
            .filter(cat -> cat.categoryValue == value)
            .findFirst();
    }
	
	
}
