package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.NotEmptyValidation;

public class NotEmptyValidationChain extends ValidationChain {

    private Class<?> annotationClass = NotEmptyValidation.class;

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == annotationClass && canHandleType(item)) {
            Object value = item.getValue();
            if (value == null || isEmpty(value)) {
                item.setErrorMessage("Cannot be empty");
                return;
            }
            return;
        }
        nextChain.validate(annotation, item);
    }

    private boolean canHandleType(Item item) {
        Object value = item.getValue();
        return value instanceof String || value instanceof Collection || value instanceof Object[]
                || value instanceof Map;
    }

    private boolean isEmpty(Object value) {
        if (value instanceof Collection)
            return ((Collection<?>) value).isEmpty();
        if (value instanceof Object[])
            return ((Object[]) value).length == 0;
        if (value instanceof Map)
            return ((Map<?, ?>) value).isEmpty();
        return value.toString().isEmpty();
    }

}
