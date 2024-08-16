package io.ljunggren.validator.evaluation.math;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LessThanOrEqualToEvaluationTest {

    @Test
    public void evaluateTest() {
        assertTrue(new LessThanOrEqualToEvaluation(5).isValid(4));
        assertTrue(new LessThanOrEqualToEvaluation(4.1).isValid(4.0));
        assertTrue(new LessThanOrEqualToEvaluation(4.1).isValid(4));
        assertTrue(new LessThanOrEqualToEvaluation(4.1).isValid(4L));
        assertTrue(new LessThanOrEqualToEvaluation(4).isValid(4));
        assertFalse(new LessThanOrEqualToEvaluation(null).isValid(5));
        assertFalse(new LessThanOrEqualToEvaluation(4).isValid(null));
        assertFalse(new LessThanOrEqualToEvaluation(3.99999).isValid(4));
    }
    
}
