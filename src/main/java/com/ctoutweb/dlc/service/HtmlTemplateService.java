package com.ctoutweb.dlc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.model.EmailTemplateInformation;
import com.ctoutweb.dlc.service.mail.EmailSubject;

@Service
public class HtmlTemplateService {
	
	public EmailTemplateInformation getTemplateFromFile(EmailSubject subject) throws IOException {
		
		EmailTemplateInformation emailTemplateInformation = getTemplateInformation(subject);			    
		InputStream fileStream = new ClassPathResource(emailTemplateInformation.getTemplatePath()).getInputStream();
		String content = this.readFromInputStream(fileStream);		
		emailTemplateInformation.setTemplateContent(content);		
		return emailTemplateInformation;
	}
	
	private String readFromInputStream(InputStream inputStream) throws IOException {
	    StringBuilder resultStringBuilder = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            resultStringBuilder.append(line).append("\n");
	        }
	    }
	  return resultStringBuilder.toString();
	}
	
	
	private EmailTemplateInformation getTemplateInformation(EmailSubject subject) {
		
		switch (subject) {
		case REGISTER :			
			return EmailTemplateInformation.builder()
					.withSubject("inscription à dlc")
					.withTemplatePath("templates/html/registerLink.html")
					.build();
		
		case RESETPASSWORD: return EmailTemplateInformation.builder()
				.withSubject("inscription à dlc")
				.withTemplatePath("html/registerLink.html")
				.build();
		default:
			throw new IllegalArgumentException("Unexpected value: ");
		}
	}
}
