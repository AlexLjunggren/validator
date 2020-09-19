package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.Alpha;
import com.ljunggren.validator.evaluation.AlphaEvaluation;
import com.ljunggren.validator.evaluation.Evaluation;

public class AlphaValidation extends ValidationChain {

    private Class<?> annotationClass = Alpha.class;
    private Evaluation<String> evaluation = new AlphaEvaluation();

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == annotationClass && item.getValue() instanceof String) {
            if (!evaluation.isValid(item.getValue().toString())) {
                item.addErrorMessage(evaluation.getErrorMessage());
                return;
            }
            return;
        }
        nextChain.validate(annotation, item);
    }

}
