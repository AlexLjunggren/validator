package com.ljunggren.validator.validation.math;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.math.Between;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.math.BetweenEvaluation;
import com.ljunggren.validator.validation.ValidationChain;

public class BetweenValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == Between.class && item.getValue() instanceof Number) {
            Number minimum = ((Between) annotation).minimum();
            Number maximum = ((Between) annotation).maximum();
            boolean inclusive = ((Between) annotation).inclusive();
            Evaluation<Number> evaluation = new BetweenEvaluation(minimum, maximum, inclusive);
            if (!evaluation.isValid((Number) item.getValue())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
