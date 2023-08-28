package com.ctoutweb.dlc.model.product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import com.ctoutweb.dlc.annotation.date.DateFormat;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.multipart.MultipartFile;

import com.ctoutweb.dlc.annotation.file.FileExtension;
import com.ctoutweb.dlc.annotation.file.FileNotNull;
import com.ctoutweb.dlc.annotation.file.FileSize;

import jakarta.validation.constraints.NotNull;

public class SaveProductRequest {
	
	@FileNotNull(message = "l'ajout d'une image est obligatoire")
	@FileSize(size = 5000000, message = "le fichier ne doit pas dépasser 5Mo")
	@FileExtension(message="seules les fichiers png et jpg sont accéptés")
	private MultipartFile file;
	
	@NotNull(message="la date d'ouverture est obligatoire")
	@DateFormat(message = "le format de la date d'ouverture n'est pas correcte")
	private Date productOpenDate;
	
	@NotNull(message = "la dlc est obligatoire")
	@DateFormat(message = "le format de la dlc n'est pas correcte")
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
