package io.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.Regex;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.RegexEvaluation;

public class RegexValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == Regex.class && isString(item.getField())) {
            String regex = ((Regex) annotation).regex();
            Evaluation<String> evaluation = new RegexEvaluation(regex);
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(((Regex) annotation).message());
            }
        }
        nextChain.validate(annotation, item);
    }

}
