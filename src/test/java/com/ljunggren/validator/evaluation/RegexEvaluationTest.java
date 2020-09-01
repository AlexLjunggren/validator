package com.ljunggren.validator.evaluation;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegexEvaluationTest {
    
    private static final String ALPHA_REGEX = "^[a-zA-Z]+$";

    @Test
    public void evaluationTest() {
        assertTrue(new RegexEvaluation(ALPHA_REGEX).evaluateAgainst("value"));
        assertFalse(new RegexEvaluation(ALPHA_REGEX).evaluateAgainst("1234"));
        assertFalse(new RegexEvaluation(ALPHA_REGEX).evaluateAgainst(null));
        assertFalse(new RegexEvaluation(null).evaluateAgainst("1234"));
    }
    
    @Test
    public void errorMessageTest() {
        RegexEvaluation evaluation = new RegexEvaluation(ALPHA_REGEX);
        assertEquals("Must match " + ALPHA_REGEX, evaluation.getErrorMessage());
    }

}
