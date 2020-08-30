package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;

public abstract class ValidationChain {

	protected ValidationChain nextChain;
	
	public ValidationChain nextChain(ValidationChain nextChain) {
		this.nextChain = nextChain;
		return this;
	}
	
	public abstract void validate(Annotation annotation, Item item);
	
	protected boolean canHandleType(Item item) {
		Object value = item.getValue();
		return value instanceof String ||
				value instanceof Integer ||
				value instanceof Long;
	}
	
	protected String getValue(Item item) {
		Object value = item.getValue();
		if (value instanceof Integer) return String.valueOf((int) value);
		if (value instanceof Long) return String.valueOf((long) value);
		return (String) value;
	}
	
}
