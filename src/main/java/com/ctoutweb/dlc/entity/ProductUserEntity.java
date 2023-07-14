package com.ctoutweb.dlc.entity;

import java.util.Date;
import java.util.Objects;

public class ProductUserEntity {
	private int id;
	private int user_id;
	private int product_id;
	private Date createdAt;
	private Date updatedAt;
	
	public ProductUserEntity() {
		
	}
	
	private ProductUserEntity(Builder builder) {
		this.id = builder.id;
		this.user_id = builder.user_id;
		this.product_id = builder.product_id;
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
	public int getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the product_id
	 */
	public int getProduct_id() {
		return product_id;
	}
	/**
	 * @param product_id the product_id to set
	 */
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
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
		return Objects.hash(createdAt, id, product_id, updatedAt, user_id);
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
		return Objects.equals(createdAt, other.createdAt) && id == other.id && product_id == other.product_id
				&& Objects.equals(updatedAt, other.updatedAt) && user_id == other.user_id;
	}
	@Override
	public String toString() {
		return "ProductUserEntity [id=" + id + ", user_id=" + user_id + ", product_id=" + product_id + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private int id;
		private int user_id;
		private int product_id;
		private Date createdAt;
		private Date updatedAt;

		private Builder() {
		}

		public Builder withId(int id) {
			this.id = id;
			return this;
		}

		public Builder withUser_id(int user_id) {
			this.user_id = user_id;
			return this;
		}

		public Builder withProduct_id(int product_id) {
			this.product_id = product_id;
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
