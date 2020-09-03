package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.EmailValidation;
import com.ljunggren.validator.evaluation.EmailEvaluation;
import com.ljunggren.validator.evaluation.Evaluation;

public class EmailValidationChain extends ValidationChain {

    private Class<?> annotationClass = EmailValidation.class;
    private Evaluation<String> evaluation = new EmailEvaluation();

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == annotationClass && item.getValue() instanceof String) {
            if (!evaluation.evaluateAgainst(item.getValue().toString())) {
                item.setErrorMessage(evaluation.getErrorMessage());
                return;
            }
            return;
        }
        nextChain.validate(annotation, item);
    }

}
