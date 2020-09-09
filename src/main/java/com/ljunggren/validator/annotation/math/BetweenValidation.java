package com.ljunggren.validator.annotation.math;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BetweenValidation {

    double minimum();
    double maximum();
    boolean inclusive() default false;
    
}
