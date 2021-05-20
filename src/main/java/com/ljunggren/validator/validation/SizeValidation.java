package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.Size;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.SizeEvaluation;

public class SizeValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == Size.class && canHandleType(item)) {
            int size = ((Size) annotation).value();
            Evaluation<Object> evaluation = new SizeEvaluation(size);
            if (!evaluation.isValid(item.getValue())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

    private boolean canHandleType(Item item) {
        Field field = item.getField();
        return isCollection(field) || isArray(field) || isMap(field);
    }
}
