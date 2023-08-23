package com.ctoutweb.dlc.annotation.date;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateFormatValidator.class)
@Documented
public @interface DateFormat {
    String message() default "la date n'est pas valide";
    Class<?>[] groups() default {};
    Class <? extends Payload>[] payload() default {};
}
