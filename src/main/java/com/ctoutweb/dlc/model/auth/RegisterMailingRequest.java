package com.ctoutweb.dlc.model.auth;

import java.util.Objects;

import com.ctoutweb.dlc.service.mail.EmailSubject;


public class RegisterMailingRequest {
	private String recipientMail;
	private EmailSubject subject;

	private RegisterMailingRequest(Builder builder) {
		this.recipientMail = builder.recipientMail;
		this.subject = builder.subject;
	}
	/**
	 * @return the email
	 */
	public String getRecipientMail() {
		return recipientMail;
	}
	/**
	 * @param email the email to set
	 */
	public void setRecipientMail(String recipientMail) {
		this.recipientMail = recipientMail;
	}
	/**
	 * @return the subject
	 */
	public EmailSubject getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(int subjectValue) {
		this.subject = EmailSubject.getSubjectFromValue(subjectValue).orElseThrow();
	}
	@Override
	public String toString() {
		return "EmailRegisterRequest [email=" + recipientMail + ", subject=" + subject + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(recipientMail, subject);
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
		return Objects.equals(recipientMail, other.recipientMail) && subject == other.subject;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String recipientMail;
		private EmailSubject subject;

		private Builder() {
		}

		public Builder withRecipientMail(String recipientMail) {
			this.recipientMail = recipientMail;
			return this;
		}

		public Builder withSubject(EmailSubject subject) {
			this.subject = subject;
			return this;
		}

		public RegisterMailingRequest build() {
			return new RegisterMailingRequest(this);
		}
	}
	
}
