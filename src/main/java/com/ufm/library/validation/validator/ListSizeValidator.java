package com.ufm.library.validation.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ufm.library.validation.annotation.ListSize;

public class ListSizeValidator implements ConstraintValidator<ListSize, List<?>> {

    private int min;
    private int max;

    @Override
    public void initialize(ListSize constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<?> list, ConstraintValidatorContext context) {
        return list.size() >= min && list.size() <= max;
    }

}
