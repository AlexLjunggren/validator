package com.ljunggren.validator.evaluation;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmailEvaluationTest {

    private Evaluation<String> evaluation = new EmailEvaluation();

    @Test
    public void evaluationTest() {
        assertTrue(evaluation.evaluateAgainst("user@email.com"));
        assertTrue(evaluation.evaluateAgainst("USER@EMAIL.COM"));
        assertTrue(evaluation.evaluateAgainst("user@us.email.com"));
        assertFalse(evaluation.evaluateAgainst("user@email"));
        assertFalse(evaluation.evaluateAgainst("user.com"));
        assertFalse(evaluation.evaluateAgainst(null));
    }

    @Test
    public void errorMessageTest() {
        assertEquals("Must be a valid email", evaluation.getErrorMessage());
    }

}
