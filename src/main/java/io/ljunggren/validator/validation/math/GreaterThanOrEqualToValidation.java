package io.ljunggren.validator.validation.math;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.math.GreaterThanOrEqualTo;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.math.GreaterThanOrEqualToEvaluation;
import io.ljunggren.validator.validation.ValidationChain;

public class GreaterThanOrEqualToValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == GreaterThanOrEqualTo.class && isNumber(item.getField())) {
            Number minimum = ((GreaterThanOrEqualTo) annotation).value();
            Evaluation<Number> evaluation = new GreaterThanOrEqualToEvaluation(minimum);
            if (!evaluation.isValid(toNumber(item.getValue()))) {
                item.addErrorMessage(((GreaterThanOrEqualTo) annotation).message());
            }
        }
        nextChain.validate(annotation, item);
    }

}
