package io.ljunggren.validator.evaluation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RegexEvaluationTest {
    
    private static final String ALPHA_REGEX = "^[a-zA-Z]+$";

    @Test
    public void evaluationTest() {
        assertTrue(new RegexEvaluation(ALPHA_REGEX).isValid("value"));
        assertFalse(new RegexEvaluation(ALPHA_REGEX).isValid("1234"));
        assertFalse(new RegexEvaluation(ALPHA_REGEX).isValid(null));
        assertFalse(new RegexEvaluation(null).isValid("1234"));
    }
    
}
