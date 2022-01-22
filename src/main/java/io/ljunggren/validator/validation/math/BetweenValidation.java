package io.ljunggren.validator.validation.math;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.math.Between;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.math.BetweenEvaluation;
import io.ljunggren.validator.validation.ValidationChain;

public class BetweenValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == Between.class && isNumber(item.getField())) {
            Number minimum = ((Between) annotation).minimum();
            Number maximum = ((Between) annotation).maximum();
            boolean inclusive = ((Between) annotation).inclusive();
            Evaluation<Number> evaluation = new BetweenEvaluation(minimum, maximum, inclusive);
            if (!evaluation.isValid(toNumber(item.getValue()))) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
