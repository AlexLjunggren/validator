package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.ExactMatchValidation;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.ExactMatchEvaluation;
import com.ljunggren.validator.evaluation.ExactMatchesEvaluation;

public class ExactMatchValidationChain extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == ExactMatchValidation.class && canHandleType(item)) {
            Evaluation<String> evaluation = getEvaluation(annotation);
            if (!evaluation.evaluateAgainst(item.getValue().toString())) {
                item.setErrorMessage(evaluation.getErrorMessage());
                return;
            }
            return;
        }
        nextChain.validate(annotation, item);
    }

    private Evaluation<String> getEvaluation(Annotation annotation) {
        ExactMatchValidation validation = (ExactMatchValidation) annotation;
        String[] valuesToMatch = validation.matches();
        boolean caseSensitive = validation.caseSensitive();
        if (valuesToMatch.length > 0) {
            return new ExactMatchesEvaluation(valuesToMatch, caseSensitive);
        }
        String valueToMatch = validation.match();
        if (valueToMatch == ExactMatchValidation.NULL) {
            valueToMatch = null;
        }
        return new ExactMatchEvaluation(valueToMatch, caseSensitive);
    }

    private boolean canHandleType(Item item) {
        Object value = item.getValue();
        return value instanceof String || value instanceof Number;
    }

}
