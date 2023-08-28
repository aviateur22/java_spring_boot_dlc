package com.ctoutweb.dlc.annotation.file;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileNotNullValidator implements ConstraintValidator<FileNotNull, MultipartFile> {

	private String message;
	@Override
	public void initialize(FileNotNull annotation) {
		this.message = annotation.message();
	}
	
	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
		
		if(file.getSize() > 0 && !file.isEmpty()) {
			System.out.println("image valide");
			System.out.println(file.getSize());
			return true;
		}		
		System.out.println("image pas valide");
		System.out.println(message);
		context.buildConstraintViolationWithTemplate(message)     
        .addConstraintViolation()
        .disableDefaultConstraintViolation();
		
		return false;
	}

}
