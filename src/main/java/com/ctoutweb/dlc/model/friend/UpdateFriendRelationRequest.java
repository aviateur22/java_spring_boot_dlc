package com.ctoutweb.dlc.model.friend;

import java.util.Objects;

public class UpdateFriendRelationRequest {
	private boolean isFriendRequestAccepted;
	private int friendId;
	/**
	 * @return the isFriendRequestAccepted
	 */
	public boolean isFriendRequestAccepted() {
		return isFriendRequestAccepted;
	}
	/**
	 * @param isFriendRequestAccepted the isFriendRequestAccepted to set
	 */
	public void setIsFriendRequestAccepted(boolean isFriendRequestAccepted) {
		this.isFriendRequestAccepted = isFriendRequestAccepted;
	}
	/**
	 * @return the friendId
	 */
	public int getFriendId() {
		return friendId;
	}
	/**
	 * @param friendId the friendId to set
	 */
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(friendId, isFriendRequestAccepted);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateFriendRelationRequest other = (UpdateFriendRelationRequest) obj;
		return friendId == other.friendId && isFriendRequestAccepted == other.isFriendRequestAccepted;
	}
	@Override
	public String toString() {
		return "FriendManagmentRequest [isFriendRequestAccepted=" + isFriendRequestAccepted + ", friendId=" + friendId
				+ "]";
	}
	
	
}
