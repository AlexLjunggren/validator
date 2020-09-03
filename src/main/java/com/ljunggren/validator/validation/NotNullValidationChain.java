package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.NotNullValidation;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.NotNullEvaluation;

public class NotNullValidationChain extends ValidationChain {

    private Class<?> annotationClass = NotNullValidation.class;
    private Evaluation<Object> evaluation = new NotNullEvaluation();

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == annotationClass) {
            if (!evaluation.evaluateAgainst(item.getValue())) {
                item.setErrorMessage(evaluation.getErrorMessage());
                return;
            }
            return;
        }
        nextChain.validate(annotation, item);
    }

}
