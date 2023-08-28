package com.ctoutweb.dlc.security.encryption;

import java.util.Arrays;
import java.util.Optional;

public enum EncryptionCategory {
	RANDOMREGISTEREMAIL(1),
	RANDOMRESETPASSWORD(2),
	RANDOMACCOUNTACTIVATION(3),
	RANDOMWORD(4);
	
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
