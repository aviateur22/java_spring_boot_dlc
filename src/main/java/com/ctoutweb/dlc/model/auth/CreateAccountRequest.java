package com.ctoutweb.dlc.model.auth;

import java.util.Objects;

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
	
	@NotBlank(message="le code de confirmation est obligatoire")
	@NotBlank(message="le code de confirmation est obligatoire")
	private String token;
	
	@NotBlank(message="token url obligatoire")
	@NotNull(message="token url obligatoire")
	private String urlToken;

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

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the urlToken
	 */
	public String getUrlToken() {
		return urlToken;
	}

	/**
	 * @param urlToken the urlToken to set
	 */
	public void setUrlToken(String urlToken) {
		this.urlToken = urlToken;
	}

	@Override
	public int hashCode() {
		return Objects.hash(confirmPassword, email, password, token, urlToken);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateAccountRequest other = (CreateAccountRequest) obj;
		return Objects.equals(confirmPassword, other.confirmPassword) && Objects.equals(email, other.email)
				&& Objects.equals(password, other.password) && Objects.equals(token, other.token)
				&& Objects.equals(urlToken, other.urlToken);
	}

	@Override
	public String toString() {
		return "CreateAccountRequest [email=" + email + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", token=" + token + ", urlToken=" + urlToken + "]";
	}
	
}
