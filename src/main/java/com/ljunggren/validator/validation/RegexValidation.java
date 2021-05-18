package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.Regex;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.RegexEvaluation;

public class RegexValidation extends ValidationChain {

    private Class<?> annotationClass = Regex.class;
    

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == annotationClass && isString(item.getField())) {
            String regex = ((Regex) annotation).regex();
            Evaluation<String> evaluation = new RegexEvaluation(regex);
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
