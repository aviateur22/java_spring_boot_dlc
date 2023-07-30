package com.ctoutweb.dlc.service.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.model.EmailTemplateInformation;
import com.ctoutweb.dlc.model.auth.RegisterMailingRequest;
import com.ctoutweb.dlc.repository.RandomTextUserRepository;
import com.ctoutweb.dlc.service.random.RandomWordService;

@Service
public class HtmlTemplateService {
	
	private final RandomWordService randomWordService;
	private final RandomTextUserRepository randomTextUserRepository;
	
	
	
	public HtmlTemplateService(RandomWordService randomWordService, RandomTextUserRepository randomTextUserRepository) {
		super();
		this.randomWordService = randomWordService;
		this.randomTextUserRepository = randomTextUserRepository;
	}

	public EmailTemplateInformation getTemplateFromFile(RegisterMailingRequest registerMailing) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {	
		
		
		// Récupération template email
		EmailTemplateInformation emailTemplateInformation = getTemplateInformation(registerMailing);
		
		return emailTemplateInformation;
	}
	
	private EmailTemplateInformation getTemplateInformation(RegisterMailingRequest registerMailing) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
		
		switch (registerMailing.getSubject()) {
		case REGISTER:
			
			HashMap<String, String> replaceWords = new HashMap<String, String>();
			
			String randomString = randomWordService.generateRandom(5);
			String encryptedRandomString = randomWordService.encryptRandomWord(randomString);
			
			// generation token 5 lettres en claire et chiffré
			replaceWords.put("token", randomString);
			replaceWords.put("email", registerMailing.getRecipientMail());
			
			String htmlTemplate = getHtmlTemplate("templates/html/registerLink.html", replaceWords) ;
			
			return EmailTemplateInformation.builder()
					.withSubject("inscription à dlc")
					.withTemplatePath("templates/html/registerLink.html")
					.withTemplateContent(htmlTemplate)
					.build();
		
		case RESETPASSWORD: return EmailTemplateInformation.builder()
				.withSubject("inscription à dlc")
				.withTemplatePath("html/registerLink.html")
				.build();
		default:
			throw new IllegalArgumentException("Unexpected value: ");
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
	
	private String getHtmlTemplate(String filePath, HashMap<String, String> replaceWords) throws IOException {
		InputStream fileStream = new ClassPathResource("templates/html/registerLink.html").getInputStream();		
		String content = this.readFromInputStream(fileStream);
		
		Iterator<Map.Entry<String, String>> iterator = replaceWords.entrySet().iterator();
		
		replaceWords.forEach((key, value) -> {
			 content.replace("!%!"+ key +"!%!", value);
		    });
		
		return content;
	}
}
