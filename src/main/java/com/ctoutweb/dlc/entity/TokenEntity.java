package com.ctoutweb.dlc.entity;

import java.util.Date;
import java.util.Objects;

public class TokenEntity {
	private int userId;
	private String token;
	private Boolean isValid;
	private Date expiredAt;
	private Date createdAt;
	private Date updatedAt;

	public TokenEntity() {
		
	}
	
	private TokenEntity(Builder builder) {
		this.userId = builder.userId;
		this.token = builder.token;
		this.isValid = builder.isValid;
		this.expiredAt = builder.expiredAt;
		this.createdAt = builder.createdAt;
		this.updatedAt = builder.updatedAt;
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
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the isValid
	 */
	public Boolean getIsValid() {
		return isValid;
	}
	/**
	 * @param isValid the isValid to set
	 */
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	/**
	 * @return the expiredAt
	 */
	public Date getExpiredAt() {
		return expiredAt;
	}
	/**
	 * @param expiredAt the expiredAt to set
	 */
	public void setExpiredAt(Date expiredAt) {
		this.expiredAt = expiredAt;
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
		return Objects.hash(createdAt, expiredAt, isValid, token, updatedAt, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenEntity other = (TokenEntity) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(expiredAt, other.expiredAt)
				&& Objects.equals(isValid, other.isValid) && Objects.equals(token, other.token)
				&& Objects.equals(updatedAt, other.updatedAt) && userId == other.userId;
	}
	@Override
	public String toString() {
		return "TokenEntity [userId=" + userId + ", token=" + token + ", isValid=" + isValid + ", expiredAt="
				+ expiredAt + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private int userId;
		private String token;
		private Boolean isValid;
		private Date expiredAt;
		private Date createdAt;
		private Date updatedAt;

		private Builder() {
		}

		public Builder withUserId(int userId) {
			this.userId = userId;
			return this;
		}

		public Builder withToken(String token) {
			this.token = token;
			return this;
		}

		public Builder withIsValid(Boolean isValid) {
			this.isValid = isValid;
			return this;
		}

		public Builder withExpiredAt(Date expiredAt) {
			this.expiredAt = expiredAt;
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

		public TokenEntity build() {
			return new TokenEntity(this);
		}
	}
	
	
}
