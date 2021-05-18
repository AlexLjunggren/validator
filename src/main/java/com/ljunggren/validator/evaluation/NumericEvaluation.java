package com.ljunggren.validator.evaluation;

public class NumericEvaluation implements Evaluation<String> {

    private final String regex = "^[0-9]+$";

    public boolean isValid(String value) {
        return value == null ? false : value.matches(regex);
    }

    public String getErrorMessage() {
        return "Must be numeric";
    }

}
