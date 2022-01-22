package io.ljunggren.validator.evaluation.length;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void errorMessageTest() {
        assertEquals("Must be of length 4", new LengthEvaluation(4).getErrorMessage());
    }

}
