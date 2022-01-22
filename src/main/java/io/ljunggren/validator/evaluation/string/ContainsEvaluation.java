package io.ljunggren.validator.evaluation.string;

import io.ljunggren.validator.evaluation.Evaluation;

public class ContainsEvaluation implements Evaluation<String> {
    
    private String text;
    private boolean caseSensitive;

    public ContainsEvaluation(String text, boolean caseSensitive) {
        this.text = text;
        this.caseSensitive = caseSensitive;
    }

    @Override
    public boolean isValid(String value) {
        if (text == null || value == null) {
            return false;
        }
        if (!caseSensitive) {
            return value.toLowerCase().contains(text.toLowerCase());
        }
        return value.contains(text) ? true : false;
    }

    @Override
    public String getErrorMessage() {
        return String.format("Must contain %s", text);
    }

}
