package io.ljunggren.validator.evaluation.length;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MinLengthEvaluationTest {

    @Test
    public void evaluationTest() {
        assertTrue(new MinLengthEvaluation(4).isValid("Alex"));
        assertTrue(new MinLengthEvaluation(4).isValid(1234));
        assertTrue(new MinLengthEvaluation(4).isValid(10.3));
        assertFalse(new MinLengthEvaluation(4).isValid(""));
        assertFalse(new MinLengthEvaluation(4).isValid(null));
        assertFalse(new MinLengthEvaluation(5).isValid("Alex"));
        assertFalse(new MinLengthEvaluation(5).isValid(10.3));
  }
    
}
