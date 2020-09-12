package com.ljunggren.validator.evaluation.string;

import com.ljunggren.validator.evaluation.Evaluation;

public class EndsWithEvaluation implements Evaluation<String> {
    
    private String endText;
    private boolean caseSensitive;

    public EndsWithEvaluation(String endText, boolean caseSensitive) {
        this.endText = endText;
        this.caseSensitive = caseSensitive;
    }

    @Override
    public boolean isValid(String value) {
        if (endText == null || value == null) {
            return false;
        }
        if (!caseSensitive) {
            return value.toLowerCase().endsWith(endText.toLowerCase());
        }
        return value.endsWith(endText) ? true : false;
    }

    @Override
    public String getErrorMessage() {
        return String.format("Must end with %s", endText);
    }

}
