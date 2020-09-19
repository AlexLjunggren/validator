package com.ljunggren.validator.validation.math;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.math.NotBetween;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.math.NotBetweenEvaluation;
import com.ljunggren.validator.validation.ValidationChain;

public class NotBetweenValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == NotBetween.class && item.getValue() instanceof Number) {
            Number minimum = ((NotBetween) annotation).minimum();
            Number maximum = ((NotBetween) annotation).maximum();
            boolean inclusive = ((NotBetween) annotation).inclusive();
            Evaluation<Number> evaluation = new NotBetweenEvaluation(minimum, maximum, inclusive);
            if (!evaluation.isValid((Number) item.getValue())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
