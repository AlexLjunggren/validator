package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.Optional;

public class OptionalValidation extends ValidationChain {
    
    // Only chain together when using .template()
    
    private Class<?> annotationClass = Optional.class;

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == annotationClass) {
            item.addErrorMessage("Optional");
        }
        nextChain.validate(annotation, item);
    }

}
