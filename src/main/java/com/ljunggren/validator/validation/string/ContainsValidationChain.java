package com.ljunggren.validator.validation.string;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.string.ContainsValidation;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.string.ContainsEvaluation;
import com.ljunggren.validator.validation.ValidationChain;

public class ContainsValidationChain extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == ContainsValidation.class && item.getValue() instanceof String) {
            String text = ((ContainsValidation) annotation).text();
            boolean caseSensitive = ((ContainsValidation) annotation).caseSensitive();
            Evaluation<String> evaluation = new ContainsEvaluation(text, caseSensitive);
            if (!evaluation.isValid(item.getValue().toString())) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
            return;
        }
        nextChain.validate(annotation, item);
    }

}
