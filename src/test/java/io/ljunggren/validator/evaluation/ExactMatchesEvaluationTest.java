package io.ljunggren.validator.evaluation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ExactMatchesEvaluationTest {

    private String[] timeZones = { "PST", "MST", "CST", "EST" };

    @Test
    public void evaluationTest() {
        assertTrue(new ExactMatchesEvaluation(timeZones, true).isValid("MST"));
        assertFalse(new ExactMatchesEvaluation(timeZones, true).isValid("GMT"));
        assertFalse(new ExactMatchesEvaluation(timeZones, true).isValid(""));
        assertFalse(new ExactMatchesEvaluation(timeZones, true).isValid(null));
        assertFalse(new ExactMatchesEvaluation(null, true).isValid("MST"));
    }

    @Test
    public void evaluationInsensitiveTest() {
        assertTrue(new ExactMatchesEvaluation(timeZones, false).isValid("mst"));
        assertFalse(new ExactMatchesEvaluation(timeZones, false).isValid("gmt"));
        assertFalse(new ExactMatchesEvaluation(timeZones, false).isValid(""));
        assertFalse(new ExactMatchesEvaluation(timeZones, false).isValid(null));
        assertFalse(new ExactMatchesEvaluation(null, false).isValid("MST"));
    }

    @Test
    public void errorMessageTest() {
        assertEquals("Value must be one of the following: PST, MST, CST, EST", new ExactMatchesEvaluation(timeZones, true).getErrorMessage());
        assertEquals("Value must be one of the following: PST, MST, CST, EST", new ExactMatchesEvaluation(timeZones, false).getErrorMessage());
    }
}
