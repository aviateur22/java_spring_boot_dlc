package com.ctoutweb.dlc.service.mail;

public interface MailService {
	void sendEmail(EmailSubject subject, String recipient);
	void sendEmailWithAttachment(EmailSubject subject, String recipient);
}
