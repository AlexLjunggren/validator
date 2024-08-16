package io.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.Email;
import io.ljunggren.validator.evaluation.EmailEvaluation;
import io.ljunggren.validator.evaluation.Evaluation;

public class EmailValidation extends ValidationChain {

    private Evaluation<String> evaluation = new EmailEvaluation();

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == Email.class && isString(item.getField())) {
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(((Email) annotation).message());
            }
        }
        nextChain.validate(annotation, item);
    }

}
