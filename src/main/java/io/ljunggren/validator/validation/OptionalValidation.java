package io.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.Optional;

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
