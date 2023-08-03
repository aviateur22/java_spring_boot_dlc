package com.ctoutweb.dlc.annotation;

import java.util.Iterator;
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
		String lastErrorElement = null;
		if(!validationErros.isEmpty()) {
			Set<String> errorsMessage = validationErros
				.stream()
				.map(error->error.getMessage())
				.collect(Collectors.toSet());	
			
			Iterator<String> errorsMessageIterator = errorsMessage.iterator();
			while(errorsMessageIterator.hasNext()){
				lastErrorElement = errorsMessageIterator.next();
				
			}
			
			throw new AnnotationException(lastErrorElement);
			
		}
		
		
	}
}
