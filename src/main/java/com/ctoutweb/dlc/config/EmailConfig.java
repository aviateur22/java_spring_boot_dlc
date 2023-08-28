package com.ctoutweb.dlc.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.ctoutweb.dlc.properties.EmailProperties;

@Configuration
public class EmailConfig {

	private final EmailProperties emailProperties;

	public EmailConfig(EmailProperties emailProperties) {
		super();
		this.emailProperties = emailProperties;		
	}
	
	@Bean
	JavaMailSender getJavaMailSender() {

		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost(emailProperties.getHost());
		javaMailSenderImpl.setPort(emailProperties.getPort());
		javaMailSenderImpl.setUsername(emailProperties.getUserName());
		javaMailSenderImpl.setPassword(emailProperties.getPassword());	
		
		Properties props = javaMailSenderImpl.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", emailProperties.getSmtpAuth());
        props.put("mail.smtp.starttls.enable", emailProperties.getSmtpStarttlsEnable());
        props.put("mail.debug", "true");
		
        return javaMailSenderImpl;
		
	}
	
	
	
	
}
