package com.ctoutweb.dlc.entity;

import java.util.Date;

public class RandomTextUserEntity {
	private int id;
	private String randomText;
	private String iv;
	private Date expiredAt;
	private int userId;
	private int categoryId;
	private Date createdAt;
	private Date updatedAt;

	private RandomTextUserEntity(Builder builder) {
		this.id = builder.id;
		this.randomText = builder.randomText;
		this.iv = builder.iv;
		this.expiredAt = builder.expiredAt;
		this.userId = builder.userId;
		this.categoryId = builder.categoryId;
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
	 * @return the randomText
	 */
	public String getRandomText() {
		return randomText;
	}
	/**
	 * @param randomText the randomText to set
	 */
	public void setRandomText(String randomText) {
		this.randomText = randomText;
	}
	/**
	 * @return the iv
	 */
	public String getIv() {
		return iv;
	}
	/**
	 * @param iv the iv to set
	 */
	public void setIv(String iv) {
		this.iv = iv;
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
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private int id;
		private String randomText;
		private String iv;
		private Date expiredAt;
		private int userId;
		private int categoryId;
		private Date createdAt;
		private Date updatedAt;

		private Builder() {
		}

		public Builder withId(int id) {
			this.id = id;
			return this;
		}

		public Builder withRandomText(String randomText) {
			this.randomText = randomText;
			return this;
		}

		public Builder withIv(String iv) {
			this.iv = iv;
			return this;
		}

		public Builder withExpiredAt(Date expiredAt) {
			this.expiredAt = expiredAt;
			return this;
		}

		public Builder withUserId(int userId) {
			this.userId = userId;
			return this;
		}

		public Builder withCategoryId(int categoryId) {
			this.categoryId = categoryId;
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

		public RandomTextUserEntity build() {
			return new RandomTextUserEntity(this);
		}
	}
	
	
	
}
