package com.ctoutweb.dlc.entity;

import java.util.Date;
import java.util.Objects;

public class UserEntity {
	private int id;
	private String email;
	private boolean isAccountCreated;
	private Date maximumAccountCreationDate;
	private Date createdAt;
	private Date updatedAt;
	
	private UserEntity(Builder builder) {
		this.id = builder.id;
		this.email = builder.email;
		this.isAccountCreated = builder.isAccountCreated;
		this.maximumAccountCreationDate = builder.maximumAccountCreationDate;
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
	/**
	 * @return the isAccountCreated
	 */
	public boolean getIsAccountCreated() {
		return isAccountCreated;
	}
	/**
	 * @param isAccountCreated the isAccountCreated to set
	 */
	public void setAccountCreated(boolean isAccountCreated) {
		this.isAccountCreated = isAccountCreated;
	}
	/**
	 * @return the maximumAccountCreationDate
	 */
	public Date getMaximumAccountCreationDate() {
		return maximumAccountCreationDate;
	}
	/**
	 * @param maximumAccountCreationDate the maximumAccountCreationDate to set
	 */
	public void setMaximumAccountCreationDate(Date maximumAccountCreationDate) {
		this.maximumAccountCreationDate = maximumAccountCreationDate;
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
		return Objects.hash(createdAt, email, id, isAccountCreated, maximumAccountCreationDate, updatedAt);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(email, other.email) && id == other.id
				&& isAccountCreated == other.isAccountCreated
				&& Objects.equals(maximumAccountCreationDate, other.maximumAccountCreationDate)
				&& Objects.equals(updatedAt, other.updatedAt);
	}
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", email=" + email + ", isAccountCreated=" + isAccountCreated
				+ ", maximumAccountCreationDate=" + maximumAccountCreationDate + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private int id;
		private String email;
		private boolean isAccountCreated;
		private Date maximumAccountCreationDate;
		private Date createdAt;
		private Date updatedAt;

		private Builder() {
		}

		public Builder withId(int id) {
			this.id = id;
			return this;
		}

		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder withIsAccountCreated(boolean isAccountCreated) {
			this.isAccountCreated = isAccountCreated;
			return this;
		}

		public Builder withMaximumAccountCreationDate(Date maximumAccountCreationDate) {
			this.maximumAccountCreationDate = maximumAccountCreationDate;
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

		public UserEntity build() {
			return new UserEntity(this);
		}
	}
}
