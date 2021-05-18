package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.DateFormat;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.DateFormatEvaluation;

public class DateFormatValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == DateFormat.class && isString(item.getField())) {
            String format = ((DateFormat) annotation).pattern();
            Evaluation<String> evaluation = new DateFormatEvaluation(format);
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
