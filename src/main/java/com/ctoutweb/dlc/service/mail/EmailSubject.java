package com.ctoutweb.dlc.service.mail;

import java.util.Arrays;
import java.util.Optional;

public enum EmailSubject {
	REGISTER(1),
	RESETPASSWORD(2),
	ACTIVATEACCOUNT(3);
	
	private int subjectValue;
	
	private EmailSubject(int value) {
			this.subjectValue = value;
	}
	
	public static Optional<EmailSubject> getSubjectFromValue(int value) {
        return Arrays.stream(values())
            .filter(sub -> sub.subjectValue == value)
            .findFirst();
    }

	
	
}
