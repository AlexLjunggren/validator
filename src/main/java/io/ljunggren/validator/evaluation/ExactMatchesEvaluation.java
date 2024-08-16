package io.ljunggren.validator.evaluation;

import java.util.Arrays;

public class ExactMatchesEvaluation implements Evaluation<String> {

    private String[] matches = {};
    boolean caseSensitive;

    public ExactMatchesEvaluation(String[] matches, boolean caseSensitive) {
        this.matches = matches;
        this.caseSensitive = caseSensitive;
    }

    @Override
    public boolean isValid(String value) {
        if (value == null || matches == null) {
            return false;
        }
        if (!caseSensitive) {
            return Arrays.stream(matches).filter(match -> value.toLowerCase().equals(match.toLowerCase())).findFirst()
                    .orElse(null) != null;
        }
        return Arrays.stream(matches).filter(match -> value.equals(match)).findFirst().orElse(null) != null;
    }

}
