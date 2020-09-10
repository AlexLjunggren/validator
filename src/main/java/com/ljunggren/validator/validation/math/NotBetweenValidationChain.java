package com.ljunggren.validator.validation.math;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.math.NotBetweenValidation;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.math.NotBetweenEvaluation;
import com.ljunggren.validator.validation.ValidationChain;

public class NotBetweenValidationChain extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == NotBetweenValidation.class && item.getValue() instanceof Number) {
            Number minimum = ((NotBetweenValidation) annotation).minimum();
            Number maximum = ((NotBetweenValidation) annotation).maximum();
            boolean inclusive = ((NotBetweenValidation) annotation).inclusive();
            Evaluation<Number> evaluation = new NotBetweenEvaluation(minimum, maximum, inclusive);
            if (!evaluation.isValid((Number) item.getValue())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
