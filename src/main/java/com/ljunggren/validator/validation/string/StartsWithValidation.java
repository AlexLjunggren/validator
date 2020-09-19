package com.ljunggren.validator.validation.string;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.string.StartsWith;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.string.StartsWithEvaluation;
import com.ljunggren.validator.validation.ValidationChain;

public class StartsWithValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == StartsWith.class && item.getValue() instanceof String) {
            String startText = ((StartsWith) annotation).startText();
            boolean caseSensitive = ((StartsWith) annotation).caseSensitive();
            Evaluation<String> evaluation = new StartsWithEvaluation(startText, caseSensitive);
            if (!evaluation.isValid(item.getValue().toString())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
            return;
        }
        nextChain.validate(annotation, item);
    }

}
