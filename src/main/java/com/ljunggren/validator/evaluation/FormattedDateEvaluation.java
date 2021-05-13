package com.ljunggren.validator.evaluation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FormattedDateEvaluation implements Evaluation<String> {
    
    private String format;
    
    public FormattedDateEvaluation(String format) {
        this.format = format;
    }

    @Override
    public boolean isValid(String value) {
        if (value == null) {
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return String.format("Must be in %s format", format);
    }

}
