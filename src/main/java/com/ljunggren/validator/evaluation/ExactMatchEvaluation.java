package com.ljunggren.validator.evaluation;

public class ExactMatchEvaluation implements Evaluation {

    private String match = "";
    private boolean caseSensitive;

    public ExactMatchEvaluation(String match, boolean caseSensitive) {
        this.match = match;
        this.caseSensitive = caseSensitive;
    }

    @Override
    public boolean evaluateAgainst(String value) {
        if (value == null || match == null) {
            return false;
        }
        if (!caseSensitive) {
            return value.toLowerCase().equals(match.toLowerCase());
        }
        return value.equals(match);
    }

    @Override
    public String getErrorMessage() {
        return String.format("Value must be '%s'", match);
    }

}
