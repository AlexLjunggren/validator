package io.ljunggren.validator.validation.string;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.string.Contains;
import io.ljunggren.validator.evaluation.Evaluation;
import io.ljunggren.validator.evaluation.string.ContainsEvaluation;
import io.ljunggren.validator.validation.ValidationChain;

public class ContainsValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == Contains.class && isString(item.getField())) {
            Contains contains = (Contains) annotation;
            String text = contains.text();
            boolean caseSensitive = contains.caseSensitive();
            Evaluation<String> evaluation = new ContainsEvaluation(text, caseSensitive);
            if (!evaluation.isValid(toString(item.getValue()))) {
                item.addErrorMessage(contains.message());
            }
        }
        nextChain.validate(annotation, item);
    }

}
