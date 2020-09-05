package com.ljunggren.validator.evaluation;

public class RegexEvaluation implements Evaluation<String> {
    
    private String regex;

    public RegexEvaluation(String regex) {
        this.regex = regex;
    }

    public boolean evaluateAgainst(String value) {
        return value == null || regex == null ? false : value.matches(regex);
    }

    public String getErrorMessage() {
        return String.format("Must match %s", regex);
    }

}
