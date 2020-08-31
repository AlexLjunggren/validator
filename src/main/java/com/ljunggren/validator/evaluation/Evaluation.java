package com.ljunggren.validator.evaluation;

public interface Evaluation {

    boolean evaluateAgainst(String value);

    String getErrorMessage();

}
