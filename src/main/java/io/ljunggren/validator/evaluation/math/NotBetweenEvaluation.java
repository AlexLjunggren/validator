package io.ljunggren.validator.evaluation.math;

import io.ljunggren.validator.evaluation.Evaluation;

public class NotBetweenEvaluation implements Evaluation<Number> {

    private Number minimum;
    private Number maximum;
    private boolean inclusive = false;
    
    public NotBetweenEvaluation(Number minimum, Number maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public NotBetweenEvaluation(Number minimum, Number maximum, boolean inclusive) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.inclusive = inclusive;
    }

    @Override
    public boolean isValid(Number value) {
        if (value == null || minimum == null || maximum == null) {
            return false;
        }
        if (inclusive) {
            return value.doubleValue() < minimum.doubleValue() || maximum.doubleValue() < value.doubleValue();
        }
        return value.doubleValue() <= minimum.doubleValue() || maximum.doubleValue() <= value.doubleValue();
    }

}
