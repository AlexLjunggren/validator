package io.ljunggren.validator.evaluation.length;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LengthEvaluationTest {

    @Test
    public void evaluationTest() {
        assertTrue(new LengthEvaluation(4).isValid("test"));
        assertTrue(new LengthEvaluation(0).isValid(""));
        assertFalse(new LengthEvaluation(0).isValid(" "));
        assertFalse(new LengthEvaluation(5).isValid("test"));
        assertFalse(new LengthEvaluation(5).isValid(null));
    }

}
