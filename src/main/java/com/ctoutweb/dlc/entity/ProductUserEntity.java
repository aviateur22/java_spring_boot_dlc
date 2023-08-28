package com.ctoutweb.dlc.entity;

import java.util.Date;
import java.util.Objects;

public class ProductUserEntity {
	private int id;
	private int userId;
	private int productId;
	private Date createdAt;
	private Date updatedAt;
	
	public ProductUserEntity() {
		
	}
	
	private ProductUserEntity(Builder builder) {
		this.id = builder.id;
		this.userId = builder.userId;
		this.productId = builder.productId;
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
	public int getProductId() {
		return productId;
	}
	/**
	 * @param product_id the product_id to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
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
		return Objects.hash(createdAt, id, productId, updatedAt, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductUserEntity other = (ProductUserEntity) obj;
		return Objects.equals(createdAt, other.createdAt) && id == other.id && productId == other.productId
				&& Objects.equals(updatedAt, other.updatedAt) && userId == other.userId;
	}
	@Override
	public String toString() {
		return "ProductUserEntity [id=" + id + ", user_id=" + userId + ", product_id=" + productId + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private int id;
		private int userId;
		private int productId;
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

		public Builder withProductId(int productId) {
			this.productId = productId;
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

		public ProductUserEntity build() {
			return new ProductUserEntity(this);
		}
	}

}
