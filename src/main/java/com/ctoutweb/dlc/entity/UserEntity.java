package com.ctoutweb.dlc.entity;

import java.util.Date;
import java.util.Objects;

public class UserEntity {
	private int id;
	private String email;
	private String password;
	private boolean isAccountActive;
	private Date accountActivationAt;
	private Date maximumAccountActivationDate;
	private Date lastLoginAt;
	private Date createdAt;
	private Date updatedAt;
	
	private UserEntity(Builder builder) {
		this.id = builder.id;
		this.email = builder.email;
		this.password = builder.password;
		this.isAccountActive = builder.isAccountActive;
		this.accountActivationAt = builder.accountActivationAt;
		this.maximumAccountActivationDate = builder.maximumAccountActivationDate;
		this.lastLoginAt = builder.lastLoginAt;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the isAccountActive
	 */
	public boolean isAccountActive() {
		return isAccountActive;
	}
	/**
	 * @param isAccountActive the isAccountActive to set
	 */
	public void setAccountActive(boolean isAccountActive) {
		this.isAccountActive = isAccountActive;
	}
	/**
	 * @return the accountActivationAt
	 */
	public Date getAccountActivationAt() {
		return accountActivationAt;
	}
	/**
	 * @param accountActivationAt the accountActivationAt to set
	 */
	public void setAccountActivationAt(Date accountActivationAt) {
		this.accountActivationAt = accountActivationAt;
	}
	/**
	 * @return the maximumAccountActivationDate
	 */
	public Date getMaximumAccountActivationDate() {
		return maximumAccountActivationDate;
	}
	/**
	 * @param maximumAccountActivationDate the maximumAccountActivationDate to set
	 */
	public void setMaximumAccountActivationDate(Date maximumAccountActivationDate) {
		this.maximumAccountActivationDate = maximumAccountActivationDate;
	}
	/**
	 * @return the lastLoginAt
	 */
	public Date getLastLoginAt() {
		return lastLoginAt;
	}
	/**
	 * @param lastLoginAt the lastLoginAt to set
	 */
	public void setLastLoginAt(Date lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
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
		return Objects.hash(accountActivationAt, createdAt, email, id, isAccountActive, lastLoginAt,
				maximumAccountActivationDate, password, updatedAt);
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
		return Objects.equals(accountActivationAt, other.accountActivationAt)
				&& Objects.equals(createdAt, other.createdAt) && Objects.equals(email, other.email) && id == other.id
				&& isAccountActive == other.isAccountActive && Objects.equals(lastLoginAt, other.lastLoginAt)
				&& Objects.equals(maximumAccountActivationDate, other.maximumAccountActivationDate)
				&& Objects.equals(password, other.password) && Objects.equals(updatedAt, other.updatedAt);
	}
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", email=" + email + ", password=" + password + ", isAccountActive="
				+ isAccountActive + ", accountActivationAt=" + accountActivationAt + ", maximumAccountActivationDate="
				+ maximumAccountActivationDate + ", lastLoginAt=" + lastLoginAt + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private int id;
		private String email;
		private String password;
		private boolean isAccountActive;
		private Date accountActivationAt;
		private Date maximumAccountActivationDate;
		private Date lastLoginAt;
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

		public Builder withPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder withIsAccountActive(boolean isAccountActive) {
			this.isAccountActive = isAccountActive;
			return this;
		}

		public Builder withAccountActivationAt(Date accountActivationAt) {
			this.accountActivationAt = accountActivationAt;
			return this;
		}

		public Builder withMaximumAccountActivationDate(Date maximumAccountActivationDate) {
			this.maximumAccountActivationDate = maximumAccountActivationDate;
			return this;
		}

		public Builder withLastLoginAt(Date lastLoginAt) {
			this.lastLoginAt = lastLoginAt;
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
