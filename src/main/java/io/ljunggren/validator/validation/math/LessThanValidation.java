package io.ljunggren.validator.validation.math;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.math.LessThan;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.math.LessThanEvaluation;
import io.ljunggren.validator.validation.ValidationChain;

public class LessThanValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == LessThan.class && isNumber(item.getField())) {
            Number maximum = ((LessThan) annotation).value();
            Evaluation<Number> evaluation = new LessThanEvaluation(maximum);
            if (!evaluation.isValid(toNumber(item.getValue()))) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
