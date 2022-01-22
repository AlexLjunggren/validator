package io.ljunggren.validator.evaluation;

public class AlphaEvaluation implements Evaluation<String> {

    private final String regex = "^[a-zA-Z]+$";

    @Override
    public boolean isValid(String value) {
        return value == null ? false : value.matches(regex);
    }

    @Override
    public String getErrorMessage() {
        return "Must be all letters";
    }

}
