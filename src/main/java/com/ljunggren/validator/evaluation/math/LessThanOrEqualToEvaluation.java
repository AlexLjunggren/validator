package com.ljunggren.validator.evaluation.math;

import com.ljunggren.validator.evaluation.Evaluation;

public class LessThanOrEqualToEvaluation implements Evaluation<Number> {

    private Number maximum;

    public LessThanOrEqualToEvaluation(Number maximum) {
        this.maximum = maximum;
    }

    @Override
    public boolean isValid(Number value) {
        if (value == null || maximum == null) {
            return false;
        }
        return value.doubleValue() <= maximum.doubleValue();
    }

    @Override
    public String getErrorMessage() {
        return String.format("Must be less than or equal to %s", maximum.toString());
    }
    
}
