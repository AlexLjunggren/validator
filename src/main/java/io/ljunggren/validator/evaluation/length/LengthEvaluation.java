package io.ljunggren.validator.evaluation.length;

import io.ljunggren.validator.evaluation.Evaluation;

public class LengthEvaluation implements Evaluation<String> {

    private int length;

    public LengthEvaluation(int length) {
        this.length = length;
    }

    @Override
    public boolean isValid(String value) {
        return value == null ? false : value.length() == length;
    }

    @Override
    public String getErrorMessage() {
        return String.format("Must be of length %d", length);
    }

}
