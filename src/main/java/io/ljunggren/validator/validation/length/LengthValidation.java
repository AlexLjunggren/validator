package io.ljunggren.validator.validation.length;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.length.Length;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.length.LengthEvaluation;
import io.ljunggren.validator.validation.ValidationChain;

public class LengthValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == Length.class && canHandleType(item)) {
            int length = ((Length) annotation).value();
            Evaluation<String> evaluation = new LengthEvaluation(length);
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

    private boolean canHandleType(Item item) {
        return isString(item.getField()) || isNumber(item.getField());
    }

}
