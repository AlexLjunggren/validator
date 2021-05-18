package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.Email;
import com.ljunggren.validator.evaluation.EmailEvaluation;
import com.ljunggren.validator.evaluation.Evaluation;

public class EmailValidation extends ValidationChain {

    private Class<?> annotationClass = Email.class;
    private Evaluation<String> evaluation = new EmailEvaluation();

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
