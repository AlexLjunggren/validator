package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.AlphaNumericValidation;
import com.ljunggren.validator.evaluation.AlphaNumericEvaluation;
import com.ljunggren.validator.evaluation.Evaluation;

public class AlphaNumericValidationChain extends ValidationChain {

    private Class<?> annotationClass = AlphaNumericValidation.class;
    private Evaluation<String> evaluation = new AlphaNumericEvaluation();

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == annotationClass && item.getValue() instanceof String) {
            if (!evaluation.isValid(item.getValue().toString())) {
                item.setErrorMessage(evaluation.getErrorMessage());
                return;
            }
            return;
        }
        nextChain.validate(annotation, item);
    }

}
