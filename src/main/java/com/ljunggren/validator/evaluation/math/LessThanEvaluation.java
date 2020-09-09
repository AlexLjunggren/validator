package com.ljunggren.validator.evaluation.math;

import com.ljunggren.validator.evaluation.Evaluation;

public class LessThanEvaluation implements Evaluation<Number> {

    private Number maximum;

    public LessThanEvaluation(Number maximum) {
        this.maximum = maximum;
    }

    @Override
    public boolean isValid(Number value) {
        if (value == null || maximum == null) {
            return false;
        }
        return value.doubleValue() < maximum.doubleValue();
    }

    @Override
    public String getErrorMessage() {
        return "Must be less than " + maximum;
    }
    
}
