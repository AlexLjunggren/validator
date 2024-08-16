package io.ljunggren.validator.evaluation.string;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EndsWithEvaluationTest {

    @Test
    public void evaluationTest() {
        assertTrue(new EndsWithEvaluation("Alex", true).isValid("This was coded by Alex"));
        assertFalse(new EndsWithEvaluation("Alex", true).isValid("Alex coded this"));
        assertFalse(new EndsWithEvaluation("Alex", true).isValid(null));
        assertFalse(new EndsWithEvaluation(null, true).isValid("This was coded by Alex"));
    }
    
    @Test
    public void evaluationCaseInsensitiveTest() {
        assertTrue(new EndsWithEvaluation("Alex", false).isValid("This was coded by Alex"));
        assertTrue(new EndsWithEvaluation("ALEX", false).isValid("This was coded by alex"));
        assertTrue(new EndsWithEvaluation("alex", false).isValid("This was coded by ALEX"));
        assertFalse(new EndsWithEvaluation("Alex", false).isValid("Alex coded this"));
        assertFalse(new EndsWithEvaluation("Alex", false).isValid(null));
        assertFalse(new EndsWithEvaluation(null, false).isValid("This was coded by Alex"));
    }
    
}
