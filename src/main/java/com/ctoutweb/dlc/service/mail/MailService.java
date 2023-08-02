package com.ctoutweb.dlc.service.mail;

import com.ctoutweb.dlc.model.auth.RegisterMailing;

public interface MailService {
	void sendEmail(RegisterMailing registerMailing);
	void sendEmailWithAttachment(RegisterMailing registerMailing);
}
