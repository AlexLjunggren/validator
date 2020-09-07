package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.SizeValidation;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.SizeEvaluation;

public class SizeValidationChain extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == SizeValidation.class && canHandleType(item)) {
            int size = ((SizeValidation) annotation).size();
            Evaluation<Object> evaluation = new SizeEvaluation(size);
            if (!evaluation.isValid(item.getValue())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
            return;
        }
        nextChain.validate(annotation, item);
    }

    private boolean canHandleType(Item item) {
        Object value = item.getValue();
        return value instanceof Collection || value instanceof Object[] || value instanceof Map;
    }
}
