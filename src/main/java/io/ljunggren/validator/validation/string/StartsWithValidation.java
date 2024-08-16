package io.ljunggren.validator.validation.string;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.string.StartsWith;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.string.StartsWithEvaluation;
import io.ljunggren.validator.validation.ValidationChain;

public class StartsWithValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == StartsWith.class && isString(item.getField())) {
            StartsWith startsWith = (StartsWith) annotation;
            String startText = startsWith.text();
            boolean caseSensitive = startsWith.caseSensitive();
            Evaluation<String> evaluation = new StartsWithEvaluation(startText, caseSensitive);
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(startsWith.message());
            }
        }
        nextChain.validate(annotation, item);
    }

}
