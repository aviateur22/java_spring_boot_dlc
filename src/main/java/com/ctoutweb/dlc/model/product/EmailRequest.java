package com.ctoutweb.dlc.model.product;

import com.ctoutweb.dlc.service.mail.EmailSubject;

public abstract class EmailRequest {
	private EmailSubject subject;

	/**
	 * @return the subject
	 */
	public EmailSubject getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(EmailSubject subject) {
		this.subject = subject;
	}
}
