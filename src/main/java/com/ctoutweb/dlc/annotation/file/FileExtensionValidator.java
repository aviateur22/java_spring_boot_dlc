package com.ctoutweb.dlc.annotation.file;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.ctoutweb.dlc.service.storage.FileType;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileExtensionValidator implements ConstraintValidator<FileExtension, MultipartFile> {

	private String message;
	private String[] acceptedFileExtension;
	
	@Override
	public void initialize(FileExtension annotation) {
		this.message = annotation.message();
		this.acceptedFileExtension = annotation.acceptedFileExtension();
	}
	
	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {		
		
		if(file.isEmpty() || file.getSize() == 0) return false;
		
		boolean isFileValid = isFileExtensionValid(file);		
		if(isFileValid) return true;
		
		context.buildConstraintViolationWithTemplate(message)
		.addConstraintViolation()
		.disableDefaultConstraintViolation();
		
		return false;
	}
	
	@SuppressWarnings("null")
	private boolean isFileExtensionValid(MultipartFile file) {
		InputStream fileStream;
		try {
			fileStream = file.getInputStream();
			byte[] magicBytes = new byte[3];		
			fileStream.read(magicBytes);
			fileStream.close();		
			
			switch(file.getContentType().toLowerCase()) {					
				
				case "image/png": {
					if(!FileType.PNG.is(magicBytes)) return false;
					return true;
				}			
				
				case "image/jpeg": {
					if(!FileType.JPEG.is(magicBytes)) return false;
					return true;
				}
							
				default: return false;
			}
		} catch (IOException e) {
			return false;
		}		
		
	}

}
