package com.ljunggren.validator.evaluation;

public interface Evaluation<T> {

    boolean evaluateAgainst(T value);

    String getErrorMessage();

}
