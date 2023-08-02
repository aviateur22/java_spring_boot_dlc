package com.ctoutweb.dlc.model.auth;

import java.util.Objects;

public class ActivateAccountRequest {
	private String email;
	private String activationToken;
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
	 * @return the activationToken
	 */
	public String getActivationToken() {
		return activationToken;
	}
	/**
	 * @param activationToken the activationToken to set
	 */
	public void setActivationToken(String activationToken) {
		this.activationToken = activationToken;
	}
	@Override
	public int hashCode() {
		return Objects.hash(activationToken, email);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivateAccountRequest other = (ActivateAccountRequest) obj;
		return Objects.equals(activationToken, other.activationToken) && Objects.equals(email, other.email);
	}
	@Override
	public String toString() {
		return "ActivateAccountRequest [email=" + email + ", activationToken=" + activationToken + "]";
	}
	
	
	
}
