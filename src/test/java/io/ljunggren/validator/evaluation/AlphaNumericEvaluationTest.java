package io.ljunggren.validator.evaluation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AlphaNumericEvaluationTest {

    private Evaluation<String> evaluation = new AlphaNumericEvaluation();

    @Test
    public void evaluationTest() {
        assertTrue(evaluation.isValid("value"));
        assertTrue(evaluation.isValid("1234"));
        assertTrue(evaluation.isValid("value1234"));
        assertFalse(evaluation.isValid("!"));
        assertFalse(evaluation.isValid(""));
        assertFalse(evaluation.isValid(null));
    }

}
