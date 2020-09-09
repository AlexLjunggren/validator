package com.ljunggren.validator.validation.math;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.math.GreaterThanOrEqualToValidation;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.math.GreaterThanOrEqualToEvaluation;
import com.ljunggren.validator.validation.ValidationChain;

public class GreaterThanOrEqualToValidationChain extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == GreaterThanOrEqualToValidation.class && item.getValue() instanceof Number) {
            Number minimum = ((GreaterThanOrEqualToValidation) annotation).minimum();
            Evaluation<Number> evaluation = new GreaterThanOrEqualToEvaluation(minimum);
            if (!evaluation.isValid((Number) item.getValue())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
