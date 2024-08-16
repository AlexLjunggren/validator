package io.ljunggren.validator.evaluation.length;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MaxLengthEvaluationTest {

    @Test
    public void evaluationTest() {
        assertTrue(new MaxLengthEvaluation(4).isValid("Alex"));
        assertTrue(new MaxLengthEvaluation(4).isValid(1234));
        assertTrue(new MaxLengthEvaluation(4).isValid(10.3));
        assertTrue(new MaxLengthEvaluation(4).isValid(""));
        assertTrue(new MaxLengthEvaluation(4).isValid(null));
        assertFalse(new MaxLengthEvaluation(3).isValid("Alex"));
        assertFalse(new MaxLengthEvaluation(3).isValid(1234));
    }

}
