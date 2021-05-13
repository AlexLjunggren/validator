package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.FormattedDate;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.FormattedDateEvaluation;

public class FormattedDateValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == FormattedDate.class && item.getValue() instanceof String) {
            String format = ((FormattedDate) annotation).format();
            Evaluation<String> evaluation = new FormattedDateEvaluation(format);
            if (!evaluation.isValid(item.getValue().toString())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
            return;
        }
        nextChain.validate(annotation, item);
    }

}
