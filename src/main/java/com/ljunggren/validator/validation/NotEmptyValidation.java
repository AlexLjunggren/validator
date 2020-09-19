package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.NotEmpty;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.NotEmptyEvaluation;

public class NotEmptyValidation extends ValidationChain {

    private Class<?> annotationClass = NotEmpty.class;
    private Evaluation<Object> evaluation = new NotEmptyEvaluation();

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == annotationClass && canHandleType(item)) {
            if (!evaluation.isValid(item.getValue())) {
                item.addErrorMessage(evaluation.getErrorMessage());
                return;
            }
        }
        nextChain.validate(annotation, item);
    }

    private boolean canHandleType(Item item) {
        Object value = item.getValue();
        return value instanceof String || value instanceof Collection || value instanceof Object[]
                || value instanceof Map;
    }

}
