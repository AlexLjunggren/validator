package com.ljunggren.validator.evaluation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ExactMatchesEvaluationTest {

    private String[] timeZones = { "PST", "MST", "CST", "EST" };

    @Test
    public void evaluationTest() {
        assertTrue(new ExactMatchesEvaluation(timeZones, true).evaluateAgainst("MST"));
        assertFalse(new ExactMatchesEvaluation(timeZones, true).evaluateAgainst("GMT"));
        assertFalse(new ExactMatchesEvaluation(timeZones, true).evaluateAgainst(""));
        assertFalse(new ExactMatchesEvaluation(timeZones, true).evaluateAgainst(null));
        assertFalse(new ExactMatchesEvaluation(null, true).evaluateAgainst("MST"));
    }

    @Test
    public void evaluationInsensitiveTest() {
        assertTrue(new ExactMatchesEvaluation(timeZones, false).evaluateAgainst("mst"));
        assertFalse(new ExactMatchesEvaluation(timeZones, false).evaluateAgainst("gmt"));
        assertFalse(new ExactMatchesEvaluation(timeZones, false).evaluateAgainst(""));
        assertFalse(new ExactMatchesEvaluation(timeZones, false).evaluateAgainst(null));
        assertFalse(new ExactMatchesEvaluation(null, false).evaluateAgainst("MST"));
    }

    @Test
    public void errorMessageTest() {
        assertEquals("Value must be one of the following: PST, MST, CST, EST", new ExactMatchesEvaluation(timeZones, true).getErrorMessage());
        assertEquals("Value must be one of the following: PST, MST, CST, EST", new ExactMatchesEvaluation(timeZones, false).getErrorMessage());
    }
}
