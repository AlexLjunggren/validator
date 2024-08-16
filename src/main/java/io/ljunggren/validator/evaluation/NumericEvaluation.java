package io.ljunggren.validator.evaluation;

public class NumericEvaluation implements Evaluation<String> {

    private final String regex = "^[0-9]+$";

    @Override
    public boolean isValid(String value) {
        return value == null ? false : value.matches(regex);
    }

}
