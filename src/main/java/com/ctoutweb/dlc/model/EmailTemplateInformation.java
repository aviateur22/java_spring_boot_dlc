package com.ctoutweb.dlc.model;

public class EmailTemplateInformation {
	private String subject;
	private String templateContent;
	private String templatePath;
	private final String from = "dlc@noreply.fr";

	private EmailTemplateInformation(Builder builder) {
		this.subject = builder.subject;		
		this.templateContent = builder.templateContent;
		this.templatePath = builder.templatePath;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the message
	 */
	public String getTemplateContent() {
		return templateContent;
	}
	/**
	 * @param message the message to set
	 */
	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}
	
	public String getTemplatePath() {
		return templatePath;
	}
	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
	public String getFrom() {
		return from;
	}
	
	public static Builder builder() {
		return new Builder();
	}

	
	public static final class Builder {
		private String subject;
		private String templateContent;	
		private String templatePath;

		private Builder() {
		}

		public Builder withSubject(String subject) {
			this.subject = subject;
			return this;
		}

		public Builder withTemplateContent(String templateContent) {
			this.templateContent = templateContent;
			return this;
		}

		public Builder withTemplatePath(String templatePath) {
			this.templatePath = templatePath;
			return this;
		}
		
		public EmailTemplateInformation build() {
			return new EmailTemplateInformation(this);
		}
	} 
	

}
