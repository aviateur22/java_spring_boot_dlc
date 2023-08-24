package com.ctoutweb.dlc.model;

import java.util.Date;
import java.util.Objects;

public class Product {
	private int productId;
	private int userId;
	private String path;
	private String fileName;
	private String imageBase64;
	private Date productEndDate;
	private Date productOpenDate;
	private Date createdAt;
	private long numberOfOpenDays;
	private long numberOfDayLeftBeforeExpired;

	public Product(Builder builder) {
		this.productId = builder.productId;
		this.userId = builder.userId;
		this.path = builder.path;
		this.fileName = builder.fileName;
		this.imageBase64 = builder.imageBase64;
		this.productEndDate = builder.productEndDate;
		this.productOpenDate = builder.productOpenDate;
		this.createdAt = builder.createdAt;
		this.numberOfOpenDays = builder.numberOfOpenDays;
		this.numberOfDayLeftBeforeExpired = builder.numberOfDayLeftBeforeExpired;
	}
	
	
	/**
	 * @return the id
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
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

	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
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

	public long getNumberOfOpenDays() {
		return numberOfOpenDays;
	}
	public void setNumberOfOpenDays(long numberOfOpenDays) {
		this.numberOfOpenDays = numberOfOpenDays;
	}

	public long getNumberOfDayLeftBeforeExpired() {
		return numberOfDayLeftBeforeExpired;
	}

	public void setNumberOfDayLeftBeforeExpired(long numberOfDayLeftBeforeExpired) {
		this.numberOfDayLeftBeforeExpired = numberOfDayLeftBeforeExpired;
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
		return Objects.hash(createdAt, productId, path, productEndDate, productOpenDate);
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
		return Objects.equals(createdAt, other.createdAt) && productId == other.productId && Objects.equals(path, other.path)
				&& Objects.equals(productEndDate, other.productEndDate)
				&& Objects.equals(productOpenDate, other.productOpenDate);
	}

	@Override
	public String toString() {
		return "Product [id=" + productId + ", path=" + path + ", productEndDate=" + productEndDate + ", productOpenDate="
				+ productOpenDate + ", createdAt=" + createdAt + "]";
	}

	public Product() {
		super();	
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	
	public static final class Builder {
		private int productId;
		private int userId;
		private String path;
		private String fileName;
		private String imageBase64;
		private Date productEndDate;
		private Date productOpenDate;
		private Date createdAt;
		private long numberOfOpenDays;
		private long numberOfDayLeftBeforeExpired;

		private Builder() {
		}

		public Builder withProductId(int productId) {
			this.productId = productId;
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
		
		public Builder withFileName(String fileName) {
			this.fileName = fileName;
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
		
		public Builder withImageBase64(String imageBase64) {
			this.imageBase64 = imageBase64;
			return this;
		}

		public Builder withCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder withNumberOfOpenDays(long numberOfOpenDays) {
			this.numberOfOpenDays = numberOfOpenDays;
			return this;
		}

		public Builder withNumberOfDayLeftBeforeExpired(long numberOfDayLeftBeforeExpired) {
			this.numberOfDayLeftBeforeExpired = numberOfDayLeftBeforeExpired;
			return this;
		}
		

		public Product build() {
			return new Product(this);
		}
	}

}
