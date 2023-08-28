package com.ctoutweb.dlc.model.friend;

import java.util.Objects;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class UpdateFriendRelationRequest {
	@NotNull(message = "le statut de la relation est obligatoire")
	private boolean isFriendRequestAccepted;
	
	@NotNull(message = "l'identifiant de l'ami est obligatoire")
	@Min(value = 1, message = "l'identifiant de l'ami est obligatoire")
	@Positive(message = "l'identifiant de l'ami est obligatoire")
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
