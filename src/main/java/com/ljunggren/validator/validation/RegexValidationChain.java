package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.RegexValidation;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.RegexEvaluation;

public class RegexValidationChain extends ValidationChain {

    private Class<?> annotationClass = RegexValidation.class;
    

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == annotationClass && item.getValue() instanceof String) {
            String regex = ((RegexValidation) annotation).regex();
            Evaluation evaluation = new RegexEvaluation(regex);
            if (!evaluation.evaluateAgainst(item.getValue().toString())) {
                item.setErrorMessage(evaluation.getErrorMessage());
                return;
            }
            return;
        }
        nextChain.validate(annotation, item);
    }

}
