package io.ljunggren.validator.evaluation;

public interface Evaluation<T> {

    boolean isValid(T value);

}
