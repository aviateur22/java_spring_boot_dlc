package com.ctoutweb.dlc.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "email")
public class EmailProperties {
	private String host;
	private int port;
	private String userName;
	private String password;
	private String smtpAuth;
	private String smtpStarttlsEnable;
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}
	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the smtpAuth
	 */
	public String getSmtpAuth() {
		return smtpAuth;
	}
	/**
	 * @param smtpAuth the smtpAuth to set
	 */
	public void setSmtpAuth(String smtpAuth) {
		this.smtpAuth = smtpAuth;
	}
	/**
	 * @return the smtpStarttlsEnable
	 */
	public String getSmtpStarttlsEnable() {
		return smtpStarttlsEnable;
	}
	/**
	 * @param smtpStarttlsEnable the smtpStarttlsEnable to set
	 */
	public void setSmtpStarttlsEnable(String smtpStarttlsEnable) {
		this.smtpStarttlsEnable = smtpStarttlsEnable;
	}

}
