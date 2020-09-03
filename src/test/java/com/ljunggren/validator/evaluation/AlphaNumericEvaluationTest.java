package com.ljunggren.validator.evaluation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AlphaNumericEvaluationTest {

    private Evaluation<String> evaluation = new AlphaNumericEvaluation();

    @Test
    public void evaluationTest() {
        assertTrue(evaluation.evaluateAgainst("value"));
        assertTrue(evaluation.evaluateAgainst("1234"));
        assertTrue(evaluation.evaluateAgainst("value1234"));
        assertFalse(evaluation.evaluateAgainst("!"));
        assertFalse(evaluation.evaluateAgainst(""));
        assertFalse(evaluation.evaluateAgainst(null));
    }

    @Test
    public void errorMessageTest() {
        assertEquals("Must be alphanumeric", evaluation.getErrorMessage());
    }

}
