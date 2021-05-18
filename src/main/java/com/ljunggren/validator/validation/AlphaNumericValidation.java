package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.AlphaNumeric;
import com.ljunggren.validator.evaluation.AlphaNumericEvaluation;
import com.ljunggren.validator.evaluation.Evaluation;

public class AlphaNumericValidation extends ValidationChain {

    private Class<?> annotationClass = AlphaNumeric.class;
    private Evaluation<String> evaluation = new AlphaNumericEvaluation();

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == annotationClass && isString(item.getField())) {
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
