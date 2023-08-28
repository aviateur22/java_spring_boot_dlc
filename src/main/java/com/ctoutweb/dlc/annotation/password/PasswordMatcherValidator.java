package com.ctoutweb.dlc.annotation.password;

import com.ctoutweb.dlc.model.auth.CreateAccountRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatcherValidator implements ConstraintValidator<PasswordMatcher, CreateAccountRequest> {

	private String message;
	
	@Override
	public void initialize(PasswordMatcher passwordMatcher) {
		this.message = passwordMatcher.message();
	}
	
	@Override
	public boolean isValid(CreateAccountRequest request, ConstraintValidatorContext context) {
		
		if(request.getPassword().equals(request.getConfirmPassword())) return true;
		
		context.buildConstraintViolationWithTemplate(message)
		.addConstraintViolation()
		.disableDefaultConstraintViolation();
		
		return false;
	}

}
