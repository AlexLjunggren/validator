package io.ljunggren.validator.evaluation.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GreaterThanEvaluationTest {

    @Test
    public void evaluateTest() {
        assertTrue(new GreaterThanEvaluation(4).isValid(5));
        assertTrue(new GreaterThanEvaluation(4.0).isValid(4.1));
        assertTrue(new GreaterThanEvaluation(4).isValid(4.1));
        assertTrue(new GreaterThanEvaluation(4L).isValid(4.1));
        assertFalse(new GreaterThanEvaluation(null).isValid(5));
        assertFalse(new GreaterThanEvaluation(4).isValid(null));
        assertFalse(new GreaterThanEvaluation(4).isValid(3.99999));
        assertFalse(new GreaterThanEvaluation(4).isValid(4));
    }
    
    @Test
    public void errorMessageTest() {
        assertEquals("Must be greater than 4",  new GreaterThanEvaluation(4).getErrorMessage());
    }

}
