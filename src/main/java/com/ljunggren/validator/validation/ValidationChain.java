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
	
}
