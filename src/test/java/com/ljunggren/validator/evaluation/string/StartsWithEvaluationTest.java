package com.ljunggren.validator.evaluation.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StartsWithEvaluationTest {

    @Test
    public void evaluationTest() {
        assertTrue(new StartsWithEvaluation("Alex", true).isValid("Alex coded this"));
        assertFalse(new StartsWithEvaluation("Alex", true).isValid("This was coded by Alex"));
        assertFalse(new StartsWithEvaluation("Alex", true).isValid(null));
        assertFalse(new StartsWithEvaluation(null, true).isValid("This was coded by Alex"));
    }
    
    @Test
    public void evaluationCaseInsensitiveTest() {
        assertTrue(new StartsWithEvaluation("Alex", false).isValid("Alex coded this"));
        assertTrue(new StartsWithEvaluation("ALEX", false).isValid("alex coded this"));
        assertTrue(new StartsWithEvaluation("alex", false).isValid("ALEX coded this"));
        assertFalse(new StartsWithEvaluation("Alex", false).isValid("This was coded by Alex"));
        assertFalse(new StartsWithEvaluation("Alex", false).isValid(null));
        assertFalse(new StartsWithEvaluation(null, false).isValid("This was coded by Alex"));
    }
    
    @Test
    public void errorMessageTest() {
        assertEquals("Must start with Alex", new StartsWithEvaluation("Alex", true).getErrorMessage());
    }

}
