package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.Numeric;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.NumericEvaluation;

public class NumericValidation extends ValidationChain {

    private Class<?> annotationClass = Numeric.class;
    private Evaluation<String> evaluation = new NumericEvaluation();

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
