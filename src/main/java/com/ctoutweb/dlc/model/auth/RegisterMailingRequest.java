package com.ctoutweb.dlc.model.auth;

import java.util.Map;
import java.util.Objects;

import com.ctoutweb.dlc.service.mail.EmailSubject;

import java.util.Collections;

public class RegisterMailingRequest {
	private EmailSubject emailSubject;
	private String email;
	private Map<String, String> wordsToReplaceInHtmlTemplate;

	private RegisterMailingRequest(Builder builder) {
		this.emailSubject = builder.emailSubject;
		this.email = builder.email;
		this.wordsToReplaceInHtmlTemplate = builder.wordsToReplaceInHtmlTemplate;
	}
	/**
	 * @return the emailSubject
	 */
	public EmailSubject getEmailSubject() {
		return emailSubject;
	}
	/**
	 * @param emailSubject the emailSubject to set
	 */
	public void setEmailSubject(EmailSubject emailSubject) {
		this.emailSubject = emailSubject;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the wordsToReplaceInHtmlTemplate
	 */
	public Map<String, String> getWordsToReplaceInHtmlTemplate() {
		return wordsToReplaceInHtmlTemplate;
	}
	/**
	 * @param wordsToReplaceInHtmlTemplate the wordsToReplaceInHtmlTemplate to set
	 */
	public void setWordsToReplaceInHtmlTemplate(Map<String, String> wordsToReplaceInHtmlTemplate) {
		this.wordsToReplaceInHtmlTemplate = wordsToReplaceInHtmlTemplate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, emailSubject, wordsToReplaceInHtmlTemplate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisterMailingRequest other = (RegisterMailingRequest) obj;
		return Objects.equals(email, other.email) && emailSubject == other.emailSubject
				&& Objects.equals(wordsToReplaceInHtmlTemplate, other.wordsToReplaceInHtmlTemplate);
	}
	@Override
	public String toString() {
		return "RegisterMailingRequest [emailSubject=" + emailSubject + ", email=" + email
				+ ", wordsToReplaceInHtmlTemplate=" + wordsToReplaceInHtmlTemplate + "]";
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private EmailSubject emailSubject;
		private String email;
		private Map<String, String> wordsToReplaceInHtmlTemplate = Collections.emptyMap();

		private Builder() {
		}

		public Builder withEmailSubject(EmailSubject emailSubject) {
			this.emailSubject = emailSubject;
			return this;
		}

		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder withWordsToReplaceInHtmlTemplate(Map<String, String> wordsToReplaceInHtmlTemplate) {
			this.wordsToReplaceInHtmlTemplate = wordsToReplaceInHtmlTemplate;
			return this;
		}

		public RegisterMailingRequest build() {
			return new RegisterMailingRequest(this);
		}
	}
	
}
