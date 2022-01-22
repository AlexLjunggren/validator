package io.ljunggren.validator.evaluation.math;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BetweenEvaluationTest {

    @Test
    public void evaluateTest() {
        assertTrue(new BetweenEvaluation(1, 3).isValid(2));
        assertTrue(new BetweenEvaluation(1, 3).isValid(1.5));
        assertFalse(new BetweenEvaluation(1, 3).isValid(1));
        assertFalse(new BetweenEvaluation(1, 3).isValid(3));
        assertFalse(new BetweenEvaluation(1, 3).isValid(-0.9));
        assertFalse(new BetweenEvaluation(1, 3).isValid(4));
        assertFalse(new BetweenEvaluation(1, 3).isValid(null));
        assertFalse(new BetweenEvaluation(null, 3).isValid(4));
        assertFalse(new BetweenEvaluation(1, null).isValid(4));
    }

    @Test
    public void evaluateInclusiveTest() {
        assertTrue(new BetweenEvaluation(1, 3, true).isValid(1));
        assertTrue(new BetweenEvaluation(1, 3, true).isValid(2));
        assertTrue(new BetweenEvaluation(1, 3, true).isValid(3));
        assertFalse(new BetweenEvaluation(1, 3).isValid(-0.9));
        assertFalse(new BetweenEvaluation(1, 3).isValid(4));
        assertFalse(new BetweenEvaluation(1, 3).isValid(null));
        assertFalse(new BetweenEvaluation(null, 3).isValid(4));
        assertFalse(new BetweenEvaluation(1, null).isValid(4));
    }

}
