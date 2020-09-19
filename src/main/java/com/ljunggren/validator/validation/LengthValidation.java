package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.Length;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.LengthEvaluation;

public class LengthValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == Length.class && canHandleType(item)) {
            int length = ((Length) annotation).length();
            Evaluation<String> evaluation = new LengthEvaluation(length);
            if (!evaluation.isValid(item.getValue().toString())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
            return;
        }
        nextChain.validate(annotation, item);
    }

    private boolean canHandleType(Item item) {
        Object value = item.getValue();
        return value instanceof String || value instanceof Integer || value instanceof Long;
    }

}
