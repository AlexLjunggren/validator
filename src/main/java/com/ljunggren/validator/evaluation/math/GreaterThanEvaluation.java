package com.ljunggren.validator.evaluation.math;

import com.ljunggren.validator.evaluation.Evaluation;

public class GreaterThanEvaluation implements Evaluation<Number> {

    private Number minimum;

    public GreaterThanEvaluation(Number minimum) {
        this.minimum = minimum;
    }

    @Override
    public boolean isValid(Number value) {
        if (value == null || minimum == null) {
            return false;
        }
        return value.doubleValue() > minimum.doubleValue();
    }

    @Override
    public String getErrorMessage() {
        return "Must be greater than " + minimum;
    }
    
}
