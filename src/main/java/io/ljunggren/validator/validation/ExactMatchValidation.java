package io.ljunggren.validator.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.ExactMatch;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.ExactMatchEvaluation;
import io.ljunggren.validator.evaluation.ExactMatchesEvaluation;

public class ExactMatchValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == ExactMatch.class && canHandleType(item)) {
            Evaluation<String> evaluation = getEvaluation(annotation);
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

    private Evaluation<String> getEvaluation(Annotation annotation) {
        ExactMatch validation = (ExactMatch) annotation;
        String[] valuesToMatch = validation.matches();
        boolean caseSensitive = validation.caseSensitive();
        if (valuesToMatch.length > 0) {
            return new ExactMatchesEvaluation(valuesToMatch, caseSensitive);
        }
        String valueToMatch = validation.match();
        if (valueToMatch == ExactMatch.NULL) {
            valueToMatch = null;
        }
        return new ExactMatchEvaluation(valueToMatch, caseSensitive);
    }

    private boolean canHandleType(Item item) {
        Field field = item.getField();
        return isString(field) || isNumber(field);
    }

}
