package com.ctoutweb.dlc.annotation.file;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileSizeValidator.class)
@Documented
public @interface FileSize {
	public String message() default "la taille ne dois pas dépasser 5Mo";
	public long size() default 500000;
	Class<?>[] groups() default {};
	Class <? extends Payload>[] payload() default {};

}
