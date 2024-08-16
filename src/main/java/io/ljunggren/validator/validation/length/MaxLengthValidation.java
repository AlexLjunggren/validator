package io.ljunggren.validator.validation.length;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.length.MaxLength;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.length.MaxLengthEvaluation;
import io.ljunggren.validator.validation.ValidationChain;

public class MaxLengthValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == MaxLength.class && canHandleType(item)) {
            int length = ((MaxLength) annotation).value();
            Evaluation<Object> evaluation = new MaxLengthEvaluation(length);
            if (!evaluation.isValid(item.getValue())) {
                item.addErrorMessage(((MaxLength) annotation).message());
            }
        }
        nextChain.validate(annotation, item);
    }
    
    private boolean canHandleType(Item item) {
        return isString(item.getField()) || isNumber(item.getField());
    }

}
