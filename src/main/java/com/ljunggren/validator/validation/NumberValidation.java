package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.Number;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.NumberEvaluation;

public class NumberValidation extends ValidationChain {

    private Class<?> annotationClass = Number.class;
    private Evaluation<String> evaluation = new NumberEvaluation();

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
