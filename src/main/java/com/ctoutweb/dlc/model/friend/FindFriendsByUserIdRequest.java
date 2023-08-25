package com.ctoutweb.dlc.model.friend;

import java.util.Objects;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class FindFriendsByUserIdRequest {
	
	@NotNull(message = "l'identifiant de l'ami est obligatoire")
	@Min(value = 1, message ="l'identifiant de l'ami est obligatoire")
	@Positive(message = "l'identifiant de l'ami est obligatoire")
	private int userId;

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FindFriendsByUserIdRequest other = (FindFriendsByUserIdRequest) obj;
		return userId == other.userId;
	}

	@Override
	public String toString() {
		return "FindFriendsRequest [userId=" + userId + "]";
	}
	
}
