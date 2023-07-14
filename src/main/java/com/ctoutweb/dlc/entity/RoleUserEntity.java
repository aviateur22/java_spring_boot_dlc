package com.ctoutweb.dlc.entity;

import java.util.Date;
import java.util.Objects;

public class RoleUserEntity {
	private int id;
	private int userId;
	private int roleId;
	private Date createdAt;
	private Date updatedAt;
	
	public RoleUserEntity() {
		
	}
	
	private RoleUserEntity(Builder builder) {
		this.id = builder.id;
		this.userId = builder.userId;
		this.roleId = builder.roleId;
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
	/**
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
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
		return Objects.hash(createdAt, id, roleId, updatedAt, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleUserEntity other = (RoleUserEntity) obj;
		return Objects.equals(createdAt, other.createdAt) && id == other.id && roleId == other.roleId
				&& Objects.equals(updatedAt, other.updatedAt) && userId == other.userId;
	}
	@Override
	public String toString() {
		return "RoleUserEntity [id=" + id + ", userId=" + userId + ", roleId=" + roleId + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private int id;
		private int userId;
		private int roleId;
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

		public Builder withRoleId(int roleId) {
			this.roleId = roleId;
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

		public RoleUserEntity build() {
			return new RoleUserEntity(this);
		}
	}
		
}
