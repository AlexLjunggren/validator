package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.NotNullValidation;

public class NotNullValidationChain extends ValidationChain {

    private Class<?> annotationClass = NotNullValidation.class;

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == annotationClass) {
            if (item.getValue() == null) {
                item.setErrorMessage("Cannot be null");
                return;
            }
            return;
        }
        nextChain.validate(annotation, item);
    }

}
