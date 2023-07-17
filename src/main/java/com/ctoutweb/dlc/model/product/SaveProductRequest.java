package com.ctoutweb.dlc.model.product;

import java.util.Date;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

public class SaveProductRequest {
	private MultipartFile file;
	private Date productOpenDate;
	private Date productEndDate;
	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
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
	@Override
	public int hashCode() {
		return Objects.hash(file, productEndDate, productOpenDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaveProductRequest other = (SaveProductRequest) obj;
		return Objects.equals(file, other.file) && Objects.equals(productEndDate, other.productEndDate)
				&& Objects.equals(productOpenDate, other.productOpenDate);
	}
	@Override
	public String toString() {
		return "SaveProductRequest [file=" + file + ", productOpenDate=" + productOpenDate + ", productEndDate="
				+ productEndDate + "]";
	}
	
	
}
