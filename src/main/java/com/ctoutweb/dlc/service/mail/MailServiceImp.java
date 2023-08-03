package com.ctoutweb.dlc.service.mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.exception.custom.EmailException;
import com.ctoutweb.dlc.model.EmailTemplateInformation;
import com.ctoutweb.dlc.model.auth.RegisterMailing;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailServiceImp implements MailService {
	
	private final JavaMailSender mailSender;
	private final HtmlTemplateService htmlTemplateService;
	
	public MailServiceImp(JavaMailSender mailSender, HtmlTemplateService htmlTemplateService) {
		super();
		this.mailSender = mailSender;
		this.htmlTemplateService = htmlTemplateService;
	}


	@Override
	public void sendEmail(RegisterMailing registerMailing)  {
		try {
			
			EmailTemplateInformation emailInformation = htmlTemplateService.getTemplateFromFile(registerMailing);			
			MimeMessage message = mailSender.createMimeMessage();			
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");		    
			helper.setText(emailInformation.getTemplateContent(), true);
			helper.setTo(registerMailing.getEmail());
			helper.setSubject(emailInformation.getSubject());
			helper.setFrom("admin@ctoutweb.fr");
			mailSender.send(message);
		} catch (Exception ex) {				
			ex.printStackTrace();
			throw new EmailException(registerMailing.getExceptionMessage());
		}	
	}

	@Override
	public void sendEmailWithAttachment(RegisterMailing registerMailing) {
		// TODO Auto-generated method stub
		
	}

	

}
