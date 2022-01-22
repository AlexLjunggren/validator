package io.ljunggren.validator.validation.length;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.length.MinLength;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.length.MinLengthEvaluation;
import io.ljunggren.validator.validation.ValidationChain;

public class MinLengthValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == MinLength.class && canHandleType(item)) {
            int length = ((MinLength) annotation).value();
            Evaluation<Object> evaluation = new MinLengthEvaluation(length);
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
