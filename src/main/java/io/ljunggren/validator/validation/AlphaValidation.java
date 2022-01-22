package io.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.Alpha;
import io.ljunggren.validator.evaluation.AlphaEvaluation;
import io.ljunggren.validator.evaluation.Evaluation;

public class AlphaValidation extends ValidationChain {

    private Class<?> annotationClass = Alpha.class;
    private Evaluation<String> evaluation = new AlphaEvaluation();

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == annotationClass && isString(item.getField())) {
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
