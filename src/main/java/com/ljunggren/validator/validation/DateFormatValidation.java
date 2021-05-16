package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.DateFormat;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.DateFormatEvaluation;

public class DateFormatValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == DateFormat.class && item.getValue() instanceof String) {
            String format = ((DateFormat) annotation).pattern();
            Evaluation<String> evaluation = new DateFormatEvaluation(format);
            if (!evaluation.isValid(item.getValue().toString())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
            return;
        }
        nextChain.validate(annotation, item);
    }

}
