package com.ljunggren.validator.validation.math;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.math.LessThan;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.math.LessThanEvaluation;
import com.ljunggren.validator.validation.ValidationChain;

public class LessThanValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == LessThan.class && item.getValue() instanceof Number) {
            Number maximum = ((LessThan) annotation).maximum();
            Evaluation<Number> evaluation = new LessThanEvaluation(maximum);
            if (!evaluation.isValid((Number) item.getValue())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
