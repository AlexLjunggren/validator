package com.ljunggren.validator.validation.math;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.math.GreaterThanValidation;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.math.GreaterThanEvaluation;
import com.ljunggren.validator.validation.ValidationChain;

public class GreaterThanValidationChain extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == GreaterThanValidation.class && item.getValue() instanceof Number) {
            Number minimum = ((GreaterThanValidation) annotation).minimum();
            Evaluation<Number> evaluation = new GreaterThanEvaluation(minimum);
            if (!evaluation.isValid((Number) item.getValue())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
