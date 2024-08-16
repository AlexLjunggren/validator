package io.ljunggren.validator.evaluation;

public class RegexEvaluation implements Evaluation<String> {
    
    private String regex;

    public RegexEvaluation(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean isValid(String value) {
        return value == null || regex == null ? false : value.matches(regex);
    }

}
