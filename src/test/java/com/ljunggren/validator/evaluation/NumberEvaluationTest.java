package com.ljunggren.validator.evaluation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NumberEvaluationTest {

    private Evaluation<String> evaluation = new NumberEvaluation();

    @Test
    public void evaluationTest() {
        assertTrue(evaluation.isValid("1"));
        assertTrue(evaluation.isValid("1.0"));
        assertTrue(evaluation.isValid("0.1"));
        assertTrue(evaluation.isValid(".1"));
        assertFalse(evaluation.isValid("a"));
        assertFalse(evaluation.isValid(""));
        assertFalse(evaluation.isValid(null));
    }
    
    @Test
    public void errorMessageTest() {
        assertEquals("Must be a number", evaluation.getErrorMessage());
    }

}
