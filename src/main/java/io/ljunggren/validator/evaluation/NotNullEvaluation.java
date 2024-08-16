package io.ljunggren.validator.evaluation;

public class NotNullEvaluation implements Evaluation<Object> {

    @Override
    public boolean isValid(Object value) {
        return value != null;
    }

}
