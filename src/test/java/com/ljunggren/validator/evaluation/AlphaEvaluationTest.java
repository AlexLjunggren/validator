package com.ljunggren.validator.evaluation;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlphaEvaluationTest {

    private Evaluation evaluation = new AlphaEvaluation();

    @Test
    public void evaluationTest() {
        assertTrue(evaluation.evaluateAgainst("value"));
        assertTrue(evaluation.evaluateAgainst("VALUE"));
        assertFalse(evaluation.evaluateAgainst("1234"));
        assertFalse(evaluation.evaluateAgainst("value1234"));
        assertFalse(evaluation.evaluateAgainst("!"));
        assertFalse(evaluation.evaluateAgainst(""));
        assertFalse(evaluation.evaluateAgainst(null));
    }

    @Test
    public void errorMessageTest() {
        assertEquals("Must be all letters", evaluation.getErrorMessage());
    }

}
