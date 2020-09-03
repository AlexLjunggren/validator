package com.ljunggren.validator.evaluation;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class NotNullEvaluationTest {
    
    private Evaluation<Object> evaluation = new NotNullEvaluation();

    @Test
    public void evaluationTest() {
        assertTrue(evaluation.evaluateAgainst("Alex"));
        assertTrue(evaluation.evaluateAgainst(new ArrayList<>()));
        assertFalse(evaluation.evaluateAgainst(null));
    }

    @Test
    public void errorMessageTest() {
        assertEquals("Cannot be null", evaluation.getErrorMessage());
    }

}
