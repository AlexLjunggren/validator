package com.ljunggren.validator.validation.length;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.length.MaxLength;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.length.MaxLengthEvaluation;
import com.ljunggren.validator.validation.ValidationChain;

public class MaxLengthValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == MaxLength.class && canHandleType(item)) {
            int length = ((MaxLength) annotation).value();
            Evaluation<Object> evaluation = new MaxLengthEvaluation(length);
            if (!evaluation.isValid(item.getValue())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }
    
    private boolean canHandleType(Item item) {
        return isString(item.getField()) || isNumber(item.getField());
    }

}
