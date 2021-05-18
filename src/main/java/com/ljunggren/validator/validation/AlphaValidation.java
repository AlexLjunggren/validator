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
        if (annotation.annotationType() == annotationClass && isString(item.getField())) {
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
