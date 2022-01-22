package io.ljunggren.validator.evaluation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NumericEvaluationTest {

    private Evaluation<String> evaluation = new NumericEvaluation();

    @Test
    public void evaluationTest() {
        assertTrue(evaluation.isValid("1234"));
        assertFalse(evaluation.isValid("value1234"));
        assertFalse(evaluation.isValid("!"));
        assertFalse(evaluation.isValid(""));
        assertFalse(evaluation.isValid(null));
    }

    @Test
    public void errorMessageTest() {
        assertEquals("Must be numeric", evaluation.getErrorMessage());
    }

}
