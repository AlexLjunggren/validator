package io.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.Regex;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.RegexEvaluation;

public class RegexValidation extends ValidationChain {

    private Class<?> annotationClass = Regex.class;
    

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == annotationClass && isString(item.getField())) {
            String regex = ((Regex) annotation).value();
            Evaluation<String> evaluation = new RegexEvaluation(regex);
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
