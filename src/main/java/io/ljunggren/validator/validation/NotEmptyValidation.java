package io.ljunggren.validator.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.NotEmpty;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.NotEmptyEvaluation;

public class NotEmptyValidation extends ValidationChain {

    private Class<?> annotationClass = NotEmpty.class;
    private Evaluation<Object> evaluation = new NotEmptyEvaluation();

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == annotationClass && canHandleType(item)) {
            if (!evaluation.isValid(item.getValue())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

    private boolean canHandleType(Item item) {
        Field field = item.getField();
        return isString(field) || isCollection(field) || isArray(field) || isMap(field);
    }

}
