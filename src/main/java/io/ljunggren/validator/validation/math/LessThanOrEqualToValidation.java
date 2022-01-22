package io.ljunggren.validator.validation.math;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.math.LessThanOrEqualTo;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.math.LessThanOrEqualToEvaluation;
import io.ljunggren.validator.validation.ValidationChain;

public class LessThanOrEqualToValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == LessThanOrEqualTo.class && isNumber(item.getField())) {
            Number maximum = ((LessThanOrEqualTo) annotation).value();
            Evaluation<Number> evaluation = new LessThanOrEqualToEvaluation(maximum);
            if (!evaluation.isValid(toNumber(item.getValue()))) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
