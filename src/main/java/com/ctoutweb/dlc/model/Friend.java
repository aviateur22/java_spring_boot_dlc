package com.ctoutweb.dlc.model;

import java.util.Date;
import java.util.Objects;

public class Friend {
	private int id;
	private int friendId;
	private String email;	
	private String nickname;
	private boolean isFriendRequestNew;
	private boolean isFriendRequestAccepted;
	private boolean isRelationAccepted;
	private boolean isRelationDeleted;
	private Date friendCreatedAt;
	private Date createdAt;

	public Friend() {
		
	}
	
	private Friend(Builder builder) {
		this.id = builder.id;
		this.friendId = builder.friendId;
		this.email = builder.email;
		this.nickname = builder.nickname;
		this.isFriendRequestNew = builder.isFriendRequestNew;
		this.isFriendRequestAccepted = builder.isFriendRequestAccepted;
		this.isRelationAccepted = builder.isFriendRelationAccepted;
		this.isRelationDeleted = builder.isRelationDeleted;
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
	public boolean getIsFriendRequestNew() {
		return isFriendRequestNew;
	}
	/**
	 * @param isFriendRequestView the isFriendRequestView to set
	 */
	public void setIsFriendRequestNew(boolean isFriendRequestNew) {
		this.isFriendRequestNew = isFriendRequestNew;
	}
	/**
	 * @return the isFriendRequestAccepted
	 */
	public boolean getIsFriendRequestAccepted() {
		return isFriendRequestAccepted;
	}
	/**
	 * @param isFriendRequestAccepted the isFriendRequestAccepted to set
	 */
	public void setIsFriendRequestAccepted(boolean isFriendRequestAccepted) {
		this.isFriendRequestAccepted = isFriendRequestAccepted;
	}
	/**
	 * @return the isFriendRelationAccepted
	 */
	public boolean getIsRelationAccepted() {
		return isRelationAccepted;
	}
	/**
	 * @param isFriendRelationAccepted the isFriendRelationAccepted to set
	 */
	public void setIsRelationAccepted(boolean isFriendRelationAccepted) {
		this.isRelationAccepted = isFriendRelationAccepted;
	}
	public boolean getIsRelationDeleted() {
		return isRelationDeleted;
	}

	public void setIsRelationDeleted(boolean isRelationDeleted) {
		this.isRelationDeleted = isRelationDeleted;
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
		return Objects.hash(friendId, id, isRelationAccepted, isFriendRequestAccepted, isFriendRequestNew,
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
				&& isFriendRequestNew == other.isFriendRequestNew && Objects.equals(nickname, other.nickname)
				&& Objects.equals(createdAt, other.createdAt);
	}
	@Override
	public String toString() {
		return "Friend [id=" + id + ", friendId=" + friendId + ", nickname=" + nickname + ", isFriendRequestNew="
				+ isFriendRequestNew + ", isFriendRequestAccepted=" + isFriendRequestAccepted
				+ ", isRelationAccepted=" + isRelationAccepted + ", updatedAt=" + createdAt + "]";
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private int id;
		private int friendId;
		private String email;	
		private String nickname;
		private boolean isFriendRequestNew;
		private boolean isFriendRequestAccepted;
		private boolean isFriendRelationAccepted;
		private boolean isRelationDeleted;
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

		public Builder withIsFriendRequestNew(boolean isFriendRequestNew) {
			this.isFriendRequestNew = isFriendRequestNew;
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
		
		public Builder withIsRelationDeleteted(boolean isRelationDeleted) {
			this.isRelationDeleted = isRelationDeleted;
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
