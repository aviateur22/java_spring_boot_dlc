package com.ctoutweb.dlc.model.auth;

import com.ctoutweb.dlc.annotation.password.PasswordConstraint;
import com.ctoutweb.dlc.annotation.password.PasswordMatcher;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@PasswordMatcher(message = "erreur confirmation mot de passe")
public class CreateAccountRequest {
	@NotNull(message = "l'email obligatoire")
	@NotBlank(message = "l'email obligatoire")
	@Email(message="format de l'email invalide")
	private String email;
	
	@NotNull(message="le mot de passe est obligatoire")
	@NotBlank(message="le mot de passe est obligatoire")
	@PasswordConstraint(message = "mot de passe non valide", length = 11)
	private String password;
	
	@NotNull(message="la confirmation de mot de passe est obligatoire")
	@NotBlank(message="la confirmation de mot de passe est obligatoire")
	private String confirmPassword;

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

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	
}
