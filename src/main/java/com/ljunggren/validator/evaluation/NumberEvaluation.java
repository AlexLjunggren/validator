package com.ljunggren.validator.evaluation;

public class NumberEvaluation implements Evaluation<String> {

    private final String regex = "^([0-9]+)?(\\.[0-9]+)?$";

    @Override
    public boolean isValid(String value) {
        return value == null || value.isEmpty() ? false : value.matches(regex);
    }

    @Override
    public String getErrorMessage() {
        return "Must be a number";
    }

}
