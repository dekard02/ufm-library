package com.ufm.library.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ufm.library.validation.validator.IsImageValidator;

@Documented
@Target({ ElementType.FIELD, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsImageValidator.class)
public @interface IsImage {
    String message() default "File phải có định dạng png, jpeg hoặc jpg";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
