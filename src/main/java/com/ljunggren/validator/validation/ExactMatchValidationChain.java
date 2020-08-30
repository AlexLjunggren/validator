package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.ExactMatchValidation;
import com.ljunggren.validator.evaluation.Evaluation;
import com.ljunggren.validator.evaluation.ExactMatchEvaluation;

public class ExactMatchValidationChain extends ValidationChain {
	
	@Override
	public void validate(Annotation annotation, Item item) {
		if (annotation.annotationType() == ExactMatchValidation.class && canHandleType(item)) {
			String valueToMatch = ((ExactMatchValidation) annotation).match();
			Evaluation evaluation = new ExactMatchEvaluation(valueToMatch);
			if (!evaluation.evaluateAgainst(getValue(item))) {
				item.setErrorMessage(evaluation.getErrorMessage());
				return;
			}
			return;
		}
		nextChain.validate(annotation, item);
	}

	private boolean canHandleType(Item item) {
		Object value = item.getValue();
		return value instanceof String ||
				value instanceof Integer ||
				value instanceof Long;
	}
	
	private String getValue(Item item) {
		Object value = item.getValue();
		if (value instanceof Integer) return String.valueOf((int) value);
		if (value instanceof Long) return String.valueOf((long) value);
		return (String) value;
	}
	
}
