package io.ljunggren.validator.validation.string;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.string.EndsWith;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.string.EndsWithEvaluation;
import io.ljunggren.validator.validation.ValidationChain;

public class EndsWithValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == EndsWith.class && isString(item.getField())) {
            EndsWith endsWith = (EndsWith) annotation;
            String endText = endsWith.text();
            boolean caseSensitive = ((EndsWith) annotation).caseSensitive();
            Evaluation<String> evaluation = new EndsWithEvaluation(endText, caseSensitive);
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(endsWith.message());
            }
        }
        nextChain.validate(annotation, item);
    }

}
