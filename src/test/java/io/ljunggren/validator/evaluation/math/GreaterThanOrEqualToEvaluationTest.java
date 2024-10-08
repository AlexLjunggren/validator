package io.ljunggren.validator.evaluation.math;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GreaterThanOrEqualToEvaluationTest {

    @Test
    public void evaluateTest() {
        assertTrue(new GreaterThanOrEqualToEvaluation(4).isValid(5));
        assertTrue(new GreaterThanOrEqualToEvaluation(4.0).isValid(4.1));
        assertTrue(new GreaterThanOrEqualToEvaluation(4).isValid(4.1));
        assertTrue(new GreaterThanOrEqualToEvaluation(4L).isValid(4.1));
        assertTrue(new GreaterThanOrEqualToEvaluation(4).isValid(4));
        assertFalse(new GreaterThanOrEqualToEvaluation(null).isValid(5));
        assertFalse(new GreaterThanOrEqualToEvaluation(4).isValid(null));
        assertFalse(new GreaterThanOrEqualToEvaluation(4).isValid(3.99999));
    }
    
}
