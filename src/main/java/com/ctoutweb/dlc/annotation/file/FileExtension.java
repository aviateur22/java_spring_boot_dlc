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
@Constraint(validatedBy = FileExtensionValidator.class)
@Documented
public @interface FileExtension {
	String message() default "le fichier n'est pas valide";
	String[] acceptedFileExtension() default {"png", "jpg"};
	Class<?>[] groups() default {};
	Class <? extends Payload>[] payload() default {};
}
