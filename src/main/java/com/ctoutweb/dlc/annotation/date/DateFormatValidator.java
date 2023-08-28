package com.ctoutweb.dlc.annotation.date;

import com.ctoutweb.dlc.exception.custom.AnnotationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormatValidator implements ConstraintValidator<DateFormat, Date> {
    private String message;
    @Override
    public void initialize(DateFormat constraintAnnotation) {
        this.message = constraintAnnotation.message();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = formatter.format(value);

            Pattern pattern = Pattern.compile("^(\\d{2,4}\\-\\d{1,2}\\-\\d{1,2})$");
            Matcher matcher = pattern.matcher(formattedDate);
            if(matcher.find()) return true;

            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        } catch (Exception ex) {
            throw new AnnotationException(message);
        }
    }
}
