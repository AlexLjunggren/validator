package com.ljunggren.validator.evaluation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

public class NotEmptyEvaluationTest {

    private Evaluation<Object> evaluation = new NotEmptyEvaluation();

    @Test
    public void evaluationTest() {
        assertTrue(evaluation.evaluateAgainst("Alex"));
        assertTrue(evaluation.evaluateAgainst(Arrays.asList(new Integer[] {1, 2, 3})));
        assertFalse(evaluation.evaluateAgainst(new ArrayList<>()));
        assertFalse(evaluation.evaluateAgainst(new HashMap<>()));
        assertFalse(evaluation.evaluateAgainst(""));
        assertFalse(evaluation.evaluateAgainst(null));
    }
    
    @Test
    public void errorMessageTest() {
        assertEquals("Cannot be empty", evaluation.getErrorMessage());
    }

}
