package io.ljunggren.validator.validation.math;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.math.NotBetween;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.math.NotBetweenEvaluation;
import io.ljunggren.validator.validation.ValidationChain;

public class NotBetweenValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == NotBetween.class && isNumber(item.getField())) {
            Number minimum = ((NotBetween) annotation).minimum();
            Number maximum = ((NotBetween) annotation).maximum();
            boolean inclusive = ((NotBetween) annotation).inclusive();
            Evaluation<Number> evaluation = new NotBetweenEvaluation(minimum, maximum, inclusive);
            if (!evaluation.isValid(toNumber(item.getValue()))) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
