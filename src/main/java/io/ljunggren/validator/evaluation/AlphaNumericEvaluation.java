package io.ljunggren.validator.evaluation;

public class AlphaNumericEvaluation implements Evaluation<String> {

    private final String regex = "^[a-zA-Z0-9]+$";

    public boolean isValid(String value) {
        return value == null ? false : value.matches(regex);
    }

    public String getErrorMessage() {
        return "Must be alphanumeric";
    }

}
