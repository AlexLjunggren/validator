package com.ljunggren.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExactMatchValidation {

	String match() default NULL;
	String[] matches() default {};
	
	public static final String NULL = "THIS IS A SPECIAL NULL VALUE - DO NOT USE";
}
