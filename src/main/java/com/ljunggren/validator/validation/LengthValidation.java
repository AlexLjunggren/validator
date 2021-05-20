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
