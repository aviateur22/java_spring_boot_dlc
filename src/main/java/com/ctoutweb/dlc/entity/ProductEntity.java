package com.ctoutweb.dlc.entity;

import java.util.Date;
import java.util.Objects;

public class ProductEntity {
	private int id;
	private String path;
	private String fileName;
	private String fileExtension;
	private Date productEndDate;
	private Date productOpenDate;
	private int userId;
	private Date createdAt;
	private Date updatedAt;
	
	public ProductEntity() {
		
	}
	
	private ProductEntity(Builder builder) {
		this.id = builder.id;
		this.path = builder.path;
		this.fileName = builder.fileName;
		this.fileExtension = builder.fileExtension;
		this.productEndDate = builder.productEndDate;
		this.productOpenDate = builder.productOpenDate;
		this.setUserId(builder.userId);
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
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the fileExtension
	 */
	public String getFileExtension() {
		return fileExtension;
	}
	/**
	 * @param fileExtension the fileExtension to set
	 */
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
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
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
		return Objects.hash(createdAt, fileExtension, fileName, id, path, productEndDate, productOpenDate, updatedAt);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductEntity other = (ProductEntity) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(fileExtension, other.fileExtension)
				&& Objects.equals(fileName, other.fileName) && id == other.id && Objects.equals(path, other.path)
				&& Objects.equals(productEndDate, other.productEndDate)
				&& Objects.equals(productOpenDate, other.productOpenDate) && Objects.equals(updatedAt, other.updatedAt);
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", path=" + path + ", fileName=" + fileName + ", fileExtension=" + fileExtension
				+ ", productEndDate=" + productEndDate + ", productOpenDate=" + productOpenDate + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private int id;
		private String path;
		private String fileName;
		private String fileExtension;
		private Date productEndDate;
		private Date productOpenDate;
		private int userId;
		private Date createdAt;
		private Date updatedAt;

		private Builder() {
		}

		public Builder withId(int id) {
			this.id = id;
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

		public Builder withFileExtension(String fileExtension) {
			this.fileExtension = fileExtension;
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
		
		public Builder withUserId(int userId) {
			this.userId = userId;
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

		public ProductEntity build() {
			return new ProductEntity(this);
		}
	}
	
	
}
