package com.ljunggren.validator.evaluation;

public interface Evaluation<T> {

    boolean isValid(T value);
    String getErrorMessage();

}
