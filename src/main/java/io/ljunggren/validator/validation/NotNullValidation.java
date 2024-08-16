package io.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.NotNull;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.NotNullEvaluation;

public class NotNullValidation extends ValidationChain {

    private Evaluation<Object> evaluation = new NotNullEvaluation();

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == NotNull.class) {
            if (!evaluation.isValid(item.getValue())) {
                item.addErrorMessage(((NotNull) annotation).message());
            }
        }
        nextChain.validate(annotation, item);
    }

}
