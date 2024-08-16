package io.ljunggren.validator.evaluation.string;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ContainsEvaluationTest {

    @Test
    public void evaluationTest() {
        assertTrue(new ContainsEvaluation("coded", true).isValid("This was coded by Alex"));
        assertFalse(new ContainsEvaluation("coded", true).isValid("This was programmed by Alex"));
        assertFalse(new ContainsEvaluation("coded", true).isValid(null));
        assertFalse(new ContainsEvaluation(null, true).isValid("This was coded by Alex"));
    }
    
    @Test
    public void evaluationCaseInsensitiveTest() {
        assertTrue(new ContainsEvaluation("coded", false).isValid("This was coded by Alex"));
        assertTrue(new ContainsEvaluation("CODED", false).isValid("This was coded by Alex"));
        assertTrue(new ContainsEvaluation("coded", false).isValid("This was CODED by Alex"));
        assertFalse(new ContainsEvaluation("coded", false).isValid("This was programmed by Alex"));
        assertFalse(new ContainsEvaluation("coded", false).isValid(null));
        assertFalse(new ContainsEvaluation(null, false).isValid("This was coded by Alex"));
    }
    
}
