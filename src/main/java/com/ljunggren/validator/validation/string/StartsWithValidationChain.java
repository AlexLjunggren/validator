package com.ljunggren.validator.validation.string;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.string.StartsWithValidation;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.string.StartsWithEvaluation;
import com.ljunggren.validator.validation.ValidationChain;

public class StartsWithValidationChain extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == StartsWithValidation.class && item.getValue() instanceof String) {
            String startText = ((StartsWithValidation) annotation).startText();
            boolean caseSensitive = ((StartsWithValidation) annotation).caseSensitive();
            Evaluation<String> evaluation = new StartsWithEvaluation(startText, caseSensitive);
            if (!evaluation.isValid(item.getValue().toString())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
            return;
        }
        nextChain.validate(annotation, item);
    }

}
