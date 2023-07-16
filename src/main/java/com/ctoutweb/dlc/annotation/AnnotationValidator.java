package com.ctoutweb.dlc.annotation;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ctoutweb.dlc.exception.custom.AnnotationException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Component
public class AnnotationValidator <T> {
	private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = validatorFactory.getValidator();

	public void validate(T request) {
		Set<ConstraintViolation<T>> validationErros = validator.validate(request);
		
		if(!validationErros.isEmpty()) {
			String firstError = validationErros
				.stream()
				.map(error->error.getMessage())
				.collect(Collectors.toSet())
				.iterator().next();
			System.out.println(firstError);
			throw new AnnotationException(firstError);
			
		}
		
		
	}
}
