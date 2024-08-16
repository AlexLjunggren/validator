package io.ljunggren.validator.evaluation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EmailEvaluationTest {

    private Evaluation<String> evaluation = new EmailEvaluation();

    @Test
    public void evaluationTest() {
        assertTrue(evaluation.isValid("user@email.com"));
        assertTrue(evaluation.isValid("USER@EMAIL.COM"));
        assertTrue(evaluation.isValid("user@us.email.com"));
        assertFalse(evaluation.isValid("user@email"));
        assertFalse(evaluation.isValid("user.com"));
        assertFalse(evaluation.isValid(null));
    }

}
