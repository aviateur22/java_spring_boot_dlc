package com.ctoutweb.dlc.service.mail;

import com.ctoutweb.dlc.model.auth.RegisterMailingRequest;

public interface MailService {
	void sendEmail(RegisterMailingRequest registerMailing);
	void sendEmailWithAttachment(RegisterMailingRequest registerMailing);
}
