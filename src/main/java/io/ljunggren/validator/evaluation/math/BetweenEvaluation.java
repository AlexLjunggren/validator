package io.ljunggren.validator.evaluation.math;

import io.ljunggren.validator.evaluation.Evaluation;

public class BetweenEvaluation implements Evaluation<Number> {

    private Number minimum;
    private Number maximum;
    private boolean inclusive = false;
    
    public BetweenEvaluation(Number minimum, Number maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public BetweenEvaluation(Number minimum, Number maximum, boolean inclusive) {
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
            return minimum.doubleValue() <= value.doubleValue() && value.doubleValue() <= maximum.doubleValue();
        }
        return minimum.doubleValue() < value.doubleValue() && value.doubleValue() < maximum.doubleValue();
    }

    @Override
    public String getErrorMessage() {
        if (inclusive) {
            return String.format("Must be between %s and %s, inclusive", minimum.toString(), maximum.toString());
        }
        return String.format("Must be between %s and %s", minimum.toString(), maximum.toString());
    }

}
