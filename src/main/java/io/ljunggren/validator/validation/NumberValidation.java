package io.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.Number;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.NumberEvaluation;

public class NumberValidation extends ValidationChain {

    private Evaluation<String> evaluation = new NumberEvaluation();

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == Number.class && isString(item.getField())) {
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(((Number) annotation).message());
            }
        }
        nextChain.validate(annotation, item);
    }

}
