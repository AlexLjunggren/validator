package io.ljunggren.validator.evaluation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AlphaEvaluationTest {

    private Evaluation<String> evaluation = new AlphaEvaluation();

    @Test
    public void evaluationTest() {
        assertTrue(evaluation.isValid("value"));
        assertTrue(evaluation.isValid("VALUE"));
        assertFalse(evaluation.isValid("1234"));
        assertFalse(evaluation.isValid("value1234"));
        assertFalse(evaluation.isValid("!"));
        assertFalse(evaluation.isValid(""));
        assertFalse(evaluation.isValid(null));
    }

}
