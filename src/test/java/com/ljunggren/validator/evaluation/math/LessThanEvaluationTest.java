package com.ljunggren.validator.evaluation.math;

import static org.junit.Assert.*;

import org.junit.Test;

public class LessThanEvaluationTest {

    @Test
    public void test() {
        assertTrue(new LessThanEvaluation(5).isValid(4));
        assertTrue(new LessThanEvaluation(4.1).isValid(4.0));
        assertTrue(new LessThanEvaluation(4.1).isValid(4));
        assertTrue(new LessThanEvaluation(4.1).isValid(4L));
        assertFalse(new LessThanEvaluation(null).isValid(5));
        assertFalse(new LessThanEvaluation(4).isValid(null));
        assertFalse(new LessThanEvaluation(3.99999).isValid(4));
        assertFalse(new LessThanEvaluation(4).isValid(4));
    }
    
    @Test
    public void errorMessageTest() {
        assertEquals("Must be less than 4",  new LessThanEvaluation(4).getErrorMessage());
    }

}
