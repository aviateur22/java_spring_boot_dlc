package com.ctoutweb.dlc.model.friend;

import java.util.Objects;

public class FindFriendsRequest {
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
		FindFriendsRequest other = (FindFriendsRequest) obj;
		return userId == other.userId;
	}

	@Override
	public String toString() {
		return "FindFriendsRequest [userId=" + userId + "]";
	}
	
}
