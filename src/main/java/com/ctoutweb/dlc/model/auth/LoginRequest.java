package com.ctoutweb.dlc.model.auth;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginRequest {
	@NotNull(message = "email obligatoire")
	@NotBlank(message = "email obligatoire")
	private String email;
	
	@NotNull(message = "mot de passe obligatoire")
	@NotBlank(message = "mot de passe obligatoire")
	private String password;
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
	@Override
	public int hashCode() {
		return Objects.hash(email, password);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginRequest other = (LoginRequest) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password);
	}
	@Override
	public String toString() {
		return "LoginRequest [email=" + email + ", password=" + password + "]";
	}
	
	

}
