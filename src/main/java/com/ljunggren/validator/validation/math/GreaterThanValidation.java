package com.ljunggren.validator.validation.math;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.math.GreaterThan;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.math.GreaterThanEvaluation;
import com.ljunggren.validator.validation.ValidationChain;

public class GreaterThanValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == GreaterThan.class && isNumber(item.getField())) {
            Number minimum = ((GreaterThan) annotation).value();
            Evaluation<Number> evaluation = new GreaterThanEvaluation(minimum);
            if (!evaluation.isValid(toNumber(item.getValue()))) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
