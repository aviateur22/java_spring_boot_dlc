package com.ctoutweb.dlc.service.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.exception.custom.EmailException;
import com.ctoutweb.dlc.model.EmailTemplateInformation;
import com.ctoutweb.dlc.model.auth.RegisterMailing;

@Service
public class HtmlTemplateService {	

	public EmailTemplateInformation getTemplateFromFile(RegisterMailing registerMailing) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {	
		
		
		// Récupération template email
		EmailTemplateInformation emailTemplateInformation = getTemplateInformation(registerMailing);
		
		return emailTemplateInformation;
	}
	
	private EmailTemplateInformation getTemplateInformation(RegisterMailing registerMailing) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
				
		switch (registerMailing.getEmailSubject()) {
		case REGISTER:
			
			String htmlTemplate = getHtmlTemplate("templates/html/registerLink.html", registerMailing.getWordsToReplaceInHtmlTemplate()) ;
			
			return EmailTemplateInformation.builder()
					.withSubject("inscription à dlc")
					.withTemplatePath("templates/html/registerLink.html")
					.withTemplateContent(htmlTemplate)
					.build();
		
		case RESETPASSWORD: return EmailTemplateInformation.builder()
				.withSubject("Création de votre compte à DLC")
				.withTemplatePath("html/registerLink.html")
				.build();
		
		case ACTIVATEACCOUNT:
			htmlTemplate = getHtmlTemplate("templates/html/activationAccount.html", registerMailing.getWordsToReplaceInHtmlTemplate()) ;
			
			return EmailTemplateInformation.builder()
				.withSubject("activation de votre compte à DLC")
				.withTemplatePath("templates/html/activationAccount.html")
				.withTemplateContent(htmlTemplate)
				.build();
		default:
			throw new EmailException("Impossible de créer le template HTML");
		}
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
	
	private String getHtmlTemplate(String filePath, Map<String, String> replaceWords) throws IOException {
		System.out.println(replaceWords);
		InputStream fileStream = new ClassPathResource(filePath).getInputStream();		
		String content = this.readFromInputStream(fileStream);
		
		for(Map.Entry<String, String> k: replaceWords.entrySet()) {
			System.out.println(k.getKey());
			content =   content.replace("!%!"+ k.getKey() +"!%!", k.getValue());
		}
		
		return content;
	}
}
