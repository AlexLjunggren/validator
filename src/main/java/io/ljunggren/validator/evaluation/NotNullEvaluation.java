package io.ljunggren.validator.evaluation;

public class NotNullEvaluation implements Evaluation<Object> {

    @Override
    public boolean isValid(Object value) {
        return value != null;
    }

    @Override
    public String getErrorMessage() {
        return "Cannot be null";
    }

}
