package io.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.Numeric;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.NumericEvaluation;

public class NumericValidation extends ValidationChain {

    private Evaluation<String> evaluation = new NumericEvaluation();

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == Numeric.class && isString(item.getField())) {
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(((Numeric) annotation).message());
            }
        }
        nextChain.validate(annotation, item);
    }

}
