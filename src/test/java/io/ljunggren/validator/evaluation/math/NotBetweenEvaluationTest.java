package io.ljunggren.validator.evaluation.math;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NotBetweenEvaluationTest {

    @Test
    public void evaluateTest() {
        assertTrue(new NotBetweenEvaluation(1, 3).isValid(0));
        assertTrue(new NotBetweenEvaluation(1, 3).isValid(1));
        assertTrue(new NotBetweenEvaluation(1, 3).isValid(3));
        assertTrue(new NotBetweenEvaluation(1, 3).isValid(3.5));
        assertFalse(new NotBetweenEvaluation(1, 3).isValid(2));
        assertFalse(new NotBetweenEvaluation(1, 3).isValid(2.99999));
        assertFalse(new NotBetweenEvaluation(1, 3).isValid(null));
        assertFalse(new NotBetweenEvaluation(null, 3).isValid(4));
        assertFalse(new NotBetweenEvaluation(1, null).isValid(4));
    }

    @Test
    public void evaluateInclusiveTest() {
        assertTrue(new NotBetweenEvaluation(1, 3, true).isValid(0));
        assertTrue(new NotBetweenEvaluation(1, 3, true).isValid(3.5));
        assertFalse(new NotBetweenEvaluation(1, 3, true).isValid(1));
        assertFalse(new NotBetweenEvaluation(1, 3, true).isValid(3));
        assertFalse(new NotBetweenEvaluation(1, 3).isValid(2));
        assertFalse(new NotBetweenEvaluation(1, 3).isValid(2.99999));
        assertFalse(new NotBetweenEvaluation(1, 3).isValid(null));
        assertFalse(new NotBetweenEvaluation(null, 3).isValid(4));
        assertFalse(new NotBetweenEvaluation(1, null).isValid(4));
    }

}
