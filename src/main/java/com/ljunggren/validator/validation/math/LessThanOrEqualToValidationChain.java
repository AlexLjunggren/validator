package com.ljunggren.validator.validation.math;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.math.LessThanOrEqualToValidation;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.math.LessThanOrEqualToEvaluation;
import com.ljunggren.validator.validation.ValidationChain;

public class LessThanOrEqualToValidationChain extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == LessThanOrEqualToValidation.class && item.getValue() instanceof Number) {
            Number maximum = ((LessThanOrEqualToValidation) annotation).maximum();
            Evaluation<Number> evaluation = new LessThanOrEqualToEvaluation(maximum);
            if (!evaluation.isValid((Number) item.getValue())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
