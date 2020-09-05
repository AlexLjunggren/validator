package com.ljunggren.validator.evaluation;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExactMatchEvaluationTest {

    @Test
    public void evaluationTest() {
        assertTrue(new ExactMatchEvaluation("test", true).isValid("test"));
        assertTrue(new ExactMatchEvaluation("1234", true).isValid("1234"));
        assertTrue(new ExactMatchEvaluation("", true).isValid(""));
        assertFalse(new ExactMatchEvaluation("test", true).isValid("1234"));
        assertFalse(new ExactMatchEvaluation("test", true).isValid(null));
        assertFalse(new ExactMatchEvaluation(null, true).isValid("test"));
    }

    @Test
    public void evaluationInsensitiveTest() {
        assertTrue(new ExactMatchEvaluation("test", false).isValid("TEST"));
        assertTrue(new ExactMatchEvaluation("1234", false).isValid("1234"));
        assertTrue(new ExactMatchEvaluation("", false).isValid(""));
        assertFalse(new ExactMatchEvaluation("test", false).isValid("1234"));
        assertFalse(new ExactMatchEvaluation("test", false).isValid(null));
        assertFalse(new ExactMatchEvaluation(null, false).isValid("test"));
    }

    @Test
    public void errorMessageTest() {
        assertEquals("Value must be 'Test'", new ExactMatchEvaluation("Test", false).getErrorMessage());
    }

}
