package com.ctoutweb.dlc.model.friend;

import java.util.Objects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class AddFriendRequest {
	@NotNull(message="l'email est obligatoire")
	@NotNull(message="l'email est obligatoire")
	@Email(message="mauvais format de l'email")
	private String email;
	
	public AddFriendRequest() {
		
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

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddFriendRequest other = (AddFriendRequest) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return "AddFriendRequest [email=" + email + "]";
	}
	
	
	
}
