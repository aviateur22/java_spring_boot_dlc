package com.ctoutweb.dlc.model.auth;

import java.util.Objects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ActivateAccountRequest {
	@Email(message="format de l'email invalide")
	@NotNull(message ="l'email obligatoire")
	@NotBlank(message="l'email obligatoire")
	private String email;
	
	@NotNull(message ="token obligatoire")
	@NotBlank(message="token obligatoire")
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
