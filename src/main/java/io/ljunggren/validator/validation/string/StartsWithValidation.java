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
            String startText = ((StartsWith) annotation).text();
            boolean caseSensitive = ((StartsWith) annotation).caseSensitive();
            Evaluation<String> evaluation = new StartsWithEvaluation(startText, caseSensitive);
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(evaluation.getErrorMessage());
            }
        }
        nextChain.validate(annotation, item);
    }

}
