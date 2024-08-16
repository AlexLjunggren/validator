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
            String format = ((DateFormat) annotation).format();
            Evaluation<String> evaluation = new DateFormatEvaluation(format);
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(((DateFormat) annotation).message());
            }
        }
        nextChain.validate(annotation, item);
    }

}
