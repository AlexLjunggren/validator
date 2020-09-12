package com.ljunggren.validator.evaluation.string;

import com.ljunggren.validator.evaluation.Evaluation;

public class StartsWithEvaluation implements Evaluation<String> {
    
    private String startText;
    private boolean caseSensitive;

    public StartsWithEvaluation(String startText, boolean caseSensitive) {
        this.startText = startText;
        this.caseSensitive = caseSensitive;
    }

    @Override
    public boolean isValid(String value) {
        if (startText == null || value == null) {
            return false;
        }
        if (!caseSensitive) {
            return value.toLowerCase().startsWith(startText.toLowerCase());
        }
        return value.startsWith(startText) ? true : false;
    }

    @Override
    public String getErrorMessage() {
        return String.format("Must start with %s", startText);
    }

}
