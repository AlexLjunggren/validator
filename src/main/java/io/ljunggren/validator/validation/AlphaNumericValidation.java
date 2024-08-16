package io.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.AlphaNumeric;
import io.ljunggren.validator.evaluation.AlphaNumericEvaluation;
import io.ljunggren.validator.evaluation.Evaluation;

public class AlphaNumericValidation extends ValidationChain {

    private Evaluation<String> evaluation = new AlphaNumericEvaluation();

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == AlphaNumeric.class && isString(item.getField())) {
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(((AlphaNumeric) annotation).message());
            }
        }
        nextChain.validate(annotation, item);
    }

}
