package io.ljunggren.validator.evaluation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class NotNullEvaluationTest {
    
    private Evaluation<Object> evaluation = new NotNullEvaluation();

    @Test
    public void evaluationTest() {
        assertTrue(evaluation.isValid("Alex"));
        assertTrue(evaluation.isValid(new ArrayList<>()));
        assertFalse(evaluation.isValid(null));
    }

    @Test
    public void errorMessageTest() {
        assertEquals("Cannot be null", evaluation.getErrorMessage());
    }

}
