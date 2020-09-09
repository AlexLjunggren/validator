package com.ljunggren.validator.validation.math;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.math.BetweenValidation;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.math.BetweenEvaluation;
import com.ljunggren.validator.validation.ValidationChain;

public class BetweenValidationChain extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == BetweenValidation.class && item.getValue() instanceof Number) {
            Number minimum = ((BetweenValidation) annotation).minimum();
            Number maximum = ((BetweenValidation) annotation).maximum();
            boolean inclusive = ((BetweenValidation) annotation).inclusive();
            Evaluation<Number> evaluation = new BetweenEvaluation(minimum, maximum, inclusive);
            if (!evaluation.isValid((Number) item.getValue())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
