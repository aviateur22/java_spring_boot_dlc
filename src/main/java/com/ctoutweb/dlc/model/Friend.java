package com.ctoutweb.dlc.model;

import java.util.Date;
import java.util.Objects;

public class Friend {
	private int id;
	private int friendId;
	private String email;	
	private String nickname;
	private boolean isFriendRequestView;
	private boolean isFriendRequestAccepted;
	private boolean isRelationAccepted;
	private Date friendCreatedAt;
	private Date createdAt;

	public Friend() {
		
	}
	
	private Friend(Builder builder) {
		this.id = builder.id;
		this.friendId = builder.friendId;
		this.email = builder.email;
		this.nickname = builder.nickname;
		this.isFriendRequestView = builder.isFriendRequestView;
		this.isFriendRequestAccepted = builder.isFriendRequestAccepted;
		this.isRelationAccepted = builder.isFriendRelationAccepted;
		this.friendCreatedAt = builder.friendCreatedAt;
		this.createdAt = builder.createdAt;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the isFriendRequestView
	 */
	public boolean isFriendRequestView() {
		return isFriendRequestView;
	}
	/**
	 * @param isFriendRequestView the isFriendRequestView to set
	 */
	public void setFriendRequestView(boolean isFriendRequestView) {
		this.isFriendRequestView = isFriendRequestView;
	}
	/**
	 * @return the isFriendRequestAccepted
	 */
	public boolean isFriendRequestAccepted() {
		return isFriendRequestAccepted;
	}
	/**
	 * @param isFriendRequestAccepted the isFriendRequestAccepted to set
	 */
	public void setFriendRequestAccepted(boolean isFriendRequestAccepted) {
		this.isFriendRequestAccepted = isFriendRequestAccepted;
	}
	/**
	 * @return the isFriendRelationAccepted
	 */
	public boolean isFriendRelationAccepted() {
		return isRelationAccepted;
	}
	/**
	 * @param isFriendRelationAccepted the isFriendRelationAccepted to set
	 */
	public void setFriendRelationAccepted(boolean isFriendRelationAccepted) {
		this.isRelationAccepted = isFriendRelationAccepted;
	}
	public Date getFriendCreatedAt() {
		return friendCreatedAt;
	}
	public void setFriendCreatedAt(Date friendCreatedAt) {
		this.friendCreatedAt = friendCreatedAt;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date updatedAt) {
		this.createdAt = updatedAt;
	}
	@Override
	public int hashCode() {
		return Objects.hash(friendId, id, isRelationAccepted, isFriendRequestAccepted, isFriendRequestView,
				nickname, createdAt);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Friend other = (Friend) obj;
		return friendId == other.friendId && id == other.id
				&& isRelationAccepted == other.isRelationAccepted
				&& isFriendRequestAccepted == other.isFriendRequestAccepted
				&& isFriendRequestView == other.isFriendRequestView && Objects.equals(nickname, other.nickname)
				&& Objects.equals(createdAt, other.createdAt);
	}
	@Override
	public String toString() {
		return "Friend [id=" + id + ", friendId=" + friendId + ", nickname=" + nickname + ", isFriendRequestView="
				+ isFriendRequestView + ", isFriendRequestAccepted=" + isFriendRequestAccepted
				+ ", isFriendRelationAccepted=" + isRelationAccepted + ", updatedAt=" + createdAt + "]";
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private int id;
		private int friendId;
		private String email;	
		private String nickname;
		private boolean isFriendRequestView;
		private boolean isFriendRequestAccepted;
		private boolean isFriendRelationAccepted;
		private Date friendCreatedAt;
		private Date createdAt;

		private Builder() {
		}

		public Builder withId(int id) {
			this.id = id;
			return this;
		}

		public Builder withFriendId(int friendId) {
			this.friendId = friendId;
			return this;
		}
		
		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder withNickname(String nickname) {
			this.nickname = nickname;
			return this;
		}

		public Builder withIsFriendRequestView(boolean isFriendRequestView) {
			this.isFriendRequestView = isFriendRequestView;
			return this;
		}

		public Builder withIsFriendRequestAccepted(boolean isFriendRequestAccepted) {
			this.isFriendRequestAccepted = isFriendRequestAccepted;
			return this;
		}

		public Builder withIsFriendRelationAccepted(boolean isFriendRelationAccepted) {
			this.isFriendRelationAccepted = isFriendRelationAccepted;
			return this;
		}
		
		public Builder withFriendCreatedAt(Date createdAt) {
			this.friendCreatedAt = createdAt;
			return this;
		}

		public Builder withCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Friend build() {
			return new Friend(this);
		}
	}	
}
