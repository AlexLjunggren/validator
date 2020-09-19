package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.NotNull;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.NotNullEvaluation;

public class NotNullValidation extends ValidationChain {

    private Class<?> annotationClass = NotNull.class;
    private Evaluation<Object> evaluation = new NotNullEvaluation();

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == annotationClass) {
            if (!evaluation.isValid(item.getValue())) {
                item.addErrorMessage(evaluation.getErrorMessage());
                return;
            }
            return;
        }
        nextChain.validate(annotation, item);
    }

}
