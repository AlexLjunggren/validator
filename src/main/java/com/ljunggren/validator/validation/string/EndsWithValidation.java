package com.ljunggren.validator.validation.string;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.string.EndsWith;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.string.EndsWithEvaluation;
import com.ljunggren.validator.validation.ValidationChain;

public class EndsWithValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == EndsWith.class && item.getValue() instanceof String) {
            String endText = ((EndsWith) annotation).endText();
            boolean caseSensitive = ((EndsWith) annotation).caseSensitive();
            Evaluation<String> evaluation = new EndsWithEvaluation(endText, caseSensitive);
            if (!evaluation.isValid(item.getValue().toString())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
            return;
        }
        nextChain.validate(annotation, item);
    }

}
