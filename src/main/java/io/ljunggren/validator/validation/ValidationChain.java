package io.ljunggren.validator.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.ClassUtils;

import io.ljunggren.validator.Item;

public abstract class ValidationChain {

    protected ValidationChain nextChain;

    public ValidationChain nextChain(ValidationChain nextChain) {
        this.nextChain = nextChain;
        return this;
    }

    public abstract void validate(Annotation annotation, Item item);
    
    protected String toString(Object object) {
        return object == null ? null : object.toString();
    }
    
    protected Number toNumber(Object object) {
        return object == null ? null : (Number) object;
    }
    
    protected boolean isString(Field field) {
        return field.getType().equals(String.class);
    }
    
    protected boolean isNumber(Field field) {
        return ClassUtils.isAssignable(field.getType(), Number.class);
    }
    
    protected boolean isCollection(Field field) {
        return ClassUtils.isAssignable(field.getType(), Collection.class);
    }
    
    protected boolean isArray(Field field) {
        return field.getType().isArray();
    }
    
    protected boolean isMap(Field field) {
        return field.getType().equals(Map.class);
    }
    
}
