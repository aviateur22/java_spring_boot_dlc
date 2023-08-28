package com.ctoutweb.dlc.entity;

import java.util.Date;
import java.util.Objects;

public class FriendEntity {
	private int id;
	private int userId;
	private int friendId;
	private String nickname;
	private Boolean isFriendRequestNew;
	private Boolean isFriendRequestAccepted;
	private Boolean isRelationAccepeted;
	private Boolean isRelationDeleted;
	private Date createdAt;
	private Date updatedAt;
	
	public FriendEntity() {
		
	}
	
	private FriendEntity(Builder builder) {
		this.id = builder.id;
		this.userId = builder.userId;
		this.friendId = builder.friendId;
		this.nickname = builder.nickname;
		this.isFriendRequestNew = builder.isFriendRequestNew;
		this.isFriendRequestAccepted = builder.isFriendRequestAccepted;
		this.isRelationAccepeted = builder.isRelationAccepeted;
		this.isRelationDeleted = builder.isRelationDeleted;
		this.createdAt = builder.createdAt;
		this.updatedAt = builder.updatedAt;
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
	 * @return the user_id
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the product_id
	 */
	public int getFriendId() {
		return friendId;
	}
	/**
	 * @param product_id the product_id to set
	 */
	public void setFriendId(int friendId) {
		this.friendId = friendId;
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
	public Boolean getisFriendRequestNew() {
		return isFriendRequestNew;
	}
	/**
	 * @param isFriendRequestView the isFriendRequestView to set
	 */
	public void setisFriendRequestNew(Boolean isFriendRequestNew) {
		this.isFriendRequestNew = isFriendRequestNew;
	}
	/**
	 * @return the isFriendRequestAccepted
	 */
	public Boolean getIsFriendRequestAccepted() {
		return isFriendRequestAccepted;
	}
	/**
	 * @param isFriendRequestAccepted the isFriendRequestAccepted to set
	 */
	public void setIsFriendRequestAccepted(Boolean isFriendRequestAccepted) {
		this.isFriendRequestAccepted = isFriendRequestAccepted;
	}
	/**
	 * @return the isRelationAccepeted
	 */
	public Boolean getIsRelationAccepeted() {
		return isRelationAccepeted;
	}
	/**
	 * @param isRelationAccepeted the isRelationAccepeted to set
	 */
	public void setIsRelationAccepeted(Boolean isRelationAccepeted) {
		this.isRelationAccepeted = isRelationAccepeted;
	}
	public Boolean getIsRelationDeleted() {
		return isRelationDeleted;
	}

	public void setIsRelationDeleted(Boolean isRelationDeleted) {
		this.isRelationDeleted = isRelationDeleted;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}
	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public int hashCode() {
		return Objects.hash(createdAt, id, isFriendRequestAccepted, isFriendRequestNew, isRelationAccepeted, nickname,
				friendId, updatedAt, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FriendEntity other = (FriendEntity) obj;
		return Objects.equals(createdAt, other.createdAt) && id == other.id
				&& Objects.equals(isFriendRequestAccepted, other.isFriendRequestAccepted)
				&& Objects.equals(isFriendRequestNew, other.isFriendRequestNew)
				&& Objects.equals(isRelationAccepeted, other.isRelationAccepeted)
				&& Objects.equals(nickname, other.nickname) && friendId == other.friendId
				&& Objects.equals(updatedAt, other.updatedAt) && userId == other.userId;
	}
	@Override
	public String toString() {
		return "FriendEntity [id=" + id + ", user_id=" + userId + ", product_id=" + friendId + ", nickname="
				+ nickname + ", isFriendRequestNew=" + isFriendRequestNew + ", isFriendRequestAccepted="
				+ isFriendRequestAccepted + ", isRelationAccepeted=" + isRelationAccepeted + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private int id;
		private int userId;
		private int friendId;
		private String nickname;
		private Boolean isFriendRequestNew;
		private Boolean isFriendRequestAccepted;
		private Boolean isRelationAccepeted;
		private Boolean isRelationDeleted;
		private Date createdAt;
		private Date updatedAt;

		private Builder() {
		}

		public Builder withId(int id) {
			this.id = id;
			return this;
		}

		public Builder withUserId(int userId) {
			this.userId = userId;
			return this;
		}

		public Builder withFriendId(int friendId) {
			this.friendId = friendId;
			return this;
		}

		public Builder withNickname(String nickname) {
			this.nickname = nickname;
			return this;
		}

		public Builder withIsFriendRequestNew(Boolean isFriendRequestNew) {
			this.isFriendRequestNew = isFriendRequestNew;
			return this;
		}

		public Builder withIsFriendRequestAccepted(Boolean isFriendRequestAccepted) {
			this.isFriendRequestAccepted = isFriendRequestAccepted;
			return this;
		}

		public Builder withIsRelationAccepeted(Boolean isRelationAccepeted) {
			this.isRelationAccepeted = isRelationAccepeted;
			return this;
		}
		
		public Builder withIsRelationDeleted(Boolean isRelationDeleted) {
			this.isRelationDeleted = isRelationDeleted;
			return this;
		}

		public Builder withCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder withUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
			return this;
		}

		public FriendEntity build() {
			return new FriendEntity(this);
		}
	}
}
