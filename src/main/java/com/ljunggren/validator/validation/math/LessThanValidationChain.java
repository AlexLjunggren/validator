package com.ljunggren.validator.validation.math;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.math.LessThanValidation;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.math.LessThanEvaluation;
import com.ljunggren.validator.validation.ValidationChain;

public class LessThanValidationChain extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == LessThanValidation.class && item.getValue() instanceof Number) {
            Number maximum = ((LessThanValidation) annotation).maximum();
            Evaluation<Number> evaluation = new LessThanEvaluation(maximum);
            if (!evaluation.isValid((Number) item.getValue())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
