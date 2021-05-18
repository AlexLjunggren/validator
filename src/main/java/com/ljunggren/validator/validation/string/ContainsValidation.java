package com.ljunggren.validator.validation.string;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.string.Contains;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.string.ContainsEvaluation;
import com.ljunggren.validator.validation.ValidationChain;

public class ContainsValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == Contains.class && isString(item.getField())) {
            String text = ((Contains) annotation).text();
            boolean caseSensitive = ((Contains) annotation).caseSensitive();
            Evaluation<String> evaluation = new ContainsEvaluation(text, caseSensitive);
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
