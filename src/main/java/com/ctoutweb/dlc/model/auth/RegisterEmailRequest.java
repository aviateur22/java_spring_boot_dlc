package com.ctoutweb.dlc.model.auth;

import java.util.Arrays;
import java.util.Objects;

import com.ctoutweb.dlc.annotation.password.PasswordMatcher;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@PasswordMatcher(message = "erreur confirmation mot de passe")
public class RegisterEmailRequest {
	@NotNull(message = "l'email obligatoire")
	@NotBlank(message = "l'email obligatoire")
	@Email(message="format de l'email invalide")
	private String email;
	private String registerId;
	private String registerRandomText;
	private String[] selectedImageRandomWords;
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
	 * @return the registerId
	 */
	public String getRegisterId() {
		return registerId;
	}
	/**
	 * @param registerId the registerId to set
	 */
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	/**
	 * @return the registerRandomText
	 */
	public String getRegisterRandomText() {
		return registerRandomText;
	}
	/**
	 * @param registerRandomText the registerRandomText to set
	 */
	public void setRegisterRandomText(String registerRandomText) {
		this.registerRandomText = registerRandomText;
	}
	/**
	 * @return the selectedImageRandomWords
	 */
	public String[] getSelectedImageRandomWords() {
		return selectedImageRandomWords;
	}
	/**
	 * @param selectedImageRandomWords the selectedImageRandomWords to set
	 */
	public void setSelectedImageRandomWords(String[] selectedImageRandomWords) {
		this.selectedImageRandomWords = selectedImageRandomWords;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(selectedImageRandomWords);
		result = prime * result + Objects.hash(email, registerId, registerRandomText);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisterEmailRequest other = (RegisterEmailRequest) obj;
		return Objects.equals(email, other.email) && Objects.equals(registerId, other.registerId)
				&& Objects.equals(registerRandomText, other.registerRandomText)
				&& Arrays.equals(selectedImageRandomWords, other.selectedImageRandomWords);
	}
	@Override
	public String toString() {
		return "RegisterEmailRequest [email=" + email + ", registerId=" + registerId + ", registerRandomText="
				+ registerRandomText + ", selectedImageRandomWords=" + Arrays.toString(selectedImageRandomWords) + "]";
	}
	
}
