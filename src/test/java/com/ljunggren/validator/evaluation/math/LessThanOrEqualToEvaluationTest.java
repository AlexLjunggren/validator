package com.ljunggren.validator.evaluation.math;

import static org.junit.Assert.*;

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
    
    @Test
    public void errorMessageTest() {
        assertEquals("Must be less than or equal to 4",  new LessThanOrEqualToEvaluation(4).getErrorMessage());
    }

}
