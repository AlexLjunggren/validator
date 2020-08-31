package com.ljunggren.validator.evaluation;

import java.util.Arrays;

public class ExactMatchesEvaluation implements Evaluation {

    private String[] matches = {};
    boolean caseSensitive;

    public ExactMatchesEvaluation(String[] matches, boolean caseSensitive) {
        this.matches = matches;
        this.caseSensitive = caseSensitive;
    }

    @Override
    public boolean evaluateAgainst(String value) {
        if (value == null || matches == null) {
            return false;
        }
        if (!caseSensitive) {
            return Arrays.stream(matches).filter(match -> value.toLowerCase().equals(match.toLowerCase())).findFirst()
                    .orElse(null) != null;
        }
        return Arrays.stream(matches).filter(match -> value.equals(match)).findFirst().orElse(null) != null;
    }

    @Override
    public String getErrorMessage() {
        return String.format("Value must be one of the following: %s", String.join(", ", matches));
    }

}
