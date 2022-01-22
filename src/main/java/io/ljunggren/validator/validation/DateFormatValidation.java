package io.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.DateFormat;
import io.ljunggren.validator.evaluation.DateFormatEvaluation;
import io.ljunggren.validator.evaluation.Evaluation;

public class DateFormatValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == DateFormat.class && isString(item.getField())) {
            String format = ((DateFormat) annotation).value();
            Evaluation<String> evaluation = new DateFormatEvaluation(format);
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
