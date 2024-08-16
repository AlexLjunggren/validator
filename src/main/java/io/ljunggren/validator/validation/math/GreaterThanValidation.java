package io.ljunggren.validator.validation.math;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.math.GreaterThan;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.math.GreaterThanEvaluation;
import io.ljunggren.validator.validation.ValidationChain;

public class GreaterThanValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == GreaterThan.class && isNumber(item.getField())) {
            Number minimum = ((GreaterThan) annotation).value();
            Evaluation<Number> evaluation = new GreaterThanEvaluation(minimum);
            if (!evaluation.isValid(toNumber(item.getValue()))) {
                item.addErrorMessage(((GreaterThan) annotation).message());
            }
        }
        nextChain.validate(annotation, item);
    }

}
