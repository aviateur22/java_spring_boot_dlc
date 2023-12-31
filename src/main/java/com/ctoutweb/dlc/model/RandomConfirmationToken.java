package com.ctoutweb.dlc.model;

import java.util.Date;

public class RandomConfirmationToken {
	private int id;
	private String token;
	private String iv;
	private Date expiredAt;
	private String category;
	private int categoryId;

	private RandomConfirmationToken(Builder builder) {
		this.id = builder.id;
		this.token = builder.token;
		this.iv = builder.iv;
		this.expiredAt = builder.expiredAt;
		this.category = builder.category;
		this.categoryId = builder.categoryId;
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
	public String getToken() {
		return token;
	}
	/**
	 * @param randomText the randomText to set
	 */
	public void setToken(String token) {
		this.token = token;
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
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
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

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private int id;
		private String token;
		private String iv;
		private Date expiredAt;
		private String category;
		private int categoryId;

		private Builder() {
		}

		public Builder withId(int id) {
			this.id = id;
			return this;
		}

		public Builder withRandomText(String token) {
			this.token = token;
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

		public Builder withCategory(String category) {
			this.category = category;
			return this;
		}

		public Builder withCategoryId(int categoryId) {
			this.categoryId = categoryId;
			return this;
		}

		public RandomConfirmationToken build() {
			return new RandomConfirmationToken(this);
		}
	}
	
	
}
