package com.ljunggren.validator.annotation.string;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EndsWithValidation {

    String endText();
    boolean caseSensitive() default true;
    
}
