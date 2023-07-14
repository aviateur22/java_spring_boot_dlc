package com.ctoutweb.dlc.model;

import java.util.Date;
import java.util.Objects;

public class Product {
	private int id;
	private int userId;
	private String path;
	private Date productEndDate;
	private Date productOpenDate;
	private Date createdAt;

	
	
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the productEndDate
	 */
	public Date getProductEndDate() {
		return productEndDate;
	}

	/**
	 * @param productEndDate the productEndDate to set
	 */
	public void setProductEndDate(Date productEndDate) {
		this.productEndDate = productEndDate;
	}

	/**
	 * @return the productOpenDate
	 */
	public Date getProductOpenDate() {
		return productOpenDate;
	}

	/**
	 * @param productOpenDate the productOpenDate to set
	 */
	public void setProductOpenDate(Date productOpenDate) {
		this.productOpenDate = productOpenDate;
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

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, id, path, productEndDate, productOpenDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(createdAt, other.createdAt) && id == other.id && Objects.equals(path, other.path)
				&& Objects.equals(productEndDate, other.productEndDate)
				&& Objects.equals(productOpenDate, other.productOpenDate);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", path=" + path + ", productEndDate=" + productEndDate + ", productOpenDate="
				+ productOpenDate + ", createdAt=" + createdAt + "]";
	}

	public Product() {
		super();	
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public Product(Builder builder) {
		this.id = builder.id;
		this.userId = builder.userId;
		this.path = builder.path;		
		this.productEndDate = builder.productEndDate;
		this.productOpenDate = builder.productOpenDate;
		this.createdAt = builder.createdAt;		
	}
	
	public static final class Builder {
		private int id;
		private int userId;
		private String path;		
		private Date productEndDate;
		private Date productOpenDate;
		private Date createdAt;		

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

		public Builder withPath(String path) {
			this.path = path;
			return this;
		}
		

		public Builder withProductEndDate(Date productEndDate) {
			this.productEndDate = productEndDate;
			return this;
		}

		public Builder withProductOpenDate(Date productOpenDate) {
			this.productOpenDate = productOpenDate;
			return this;
		}

		public Builder withCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
			return this;
		}
		

		public Product build() {
			return new Product(this);
		}
	}

}
