package com.ufm.library.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ufm.library.validation.validator.RecordItemsValidator;

@Documented
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RecordItemsValidator.class)
public @interface ValidRecordItems {
    String message() default "Thông tin sách mượn không hợp lệ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
