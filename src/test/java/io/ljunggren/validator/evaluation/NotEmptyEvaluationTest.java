package io.ljunggren.validator.evaluation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

public class NotEmptyEvaluationTest {

    private Evaluation<Object> evaluation = new NotEmptyEvaluation();

    @Test
    public void evaluationTest() {
        assertTrue(evaluation.isValid("Alex"));
        assertTrue(evaluation.isValid(Arrays.asList(new Integer[] {1, 2, 3})));
        assertFalse(evaluation.isValid(new ArrayList<>()));
        assertFalse(evaluation.isValid(new HashMap<>()));
        assertFalse(evaluation.isValid(""));
        assertFalse(evaluation.isValid(null));
    }
    
}
