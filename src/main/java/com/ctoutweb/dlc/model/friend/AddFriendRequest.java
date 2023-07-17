package com.ctoutweb.dlc.model.friend;

import java.util.Objects;

public class AddFriendRequest {
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
