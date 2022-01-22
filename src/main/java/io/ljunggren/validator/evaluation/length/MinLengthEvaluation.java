package io.ljunggren.validator.evaluation.length;

import io.ljunggren.validator.evaluation.Evaluation;

public class MinLengthEvaluation implements Evaluation<Object> {

    private int length;

    public MinLengthEvaluation(int length) {
        this.length = length;
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return false;
        }
        return String.valueOf(value).length() >= length;
    }
    
    @Override
    public String getErrorMessage() {
        return String.format("Must not be less than length of %d", length);
    }

}
