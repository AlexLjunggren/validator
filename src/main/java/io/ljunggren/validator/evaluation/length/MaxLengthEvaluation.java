package io.ljunggren.validator.evaluation.length;

import io.ljunggren.validator.evaluation.Evaluation;

public class MaxLengthEvaluation implements Evaluation<Object> {

    private int length;

    public MaxLengthEvaluation(int length) {
        this.length = length;
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return true;
        }
        return String.valueOf(value).length() <= length;
    }
    
}
