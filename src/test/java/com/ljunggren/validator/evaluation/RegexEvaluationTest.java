package com.ljunggren.validator.evaluation;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegexEvaluationTest {
    
    private static final String ALPHA_REGEX = "^[a-zA-Z]+$";

    @Test
    public void evaluationTest() {
        assertTrue(new RegexEvaluation(ALPHA_REGEX).isValid("value"));
        assertFalse(new RegexEvaluation(ALPHA_REGEX).isValid("1234"));
        assertFalse(new RegexEvaluation(ALPHA_REGEX).isValid(null));
        assertFalse(new RegexEvaluation(null).isValid("1234"));
    }
    
    @Test
    public void errorMessageTest() {
        RegexEvaluation evaluation = new RegexEvaluation(ALPHA_REGEX);
        assertEquals("Must match " + ALPHA_REGEX, evaluation.getErrorMessage());
    }

}
