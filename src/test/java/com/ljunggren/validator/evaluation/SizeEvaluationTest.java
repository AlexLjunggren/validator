package com.ljunggren.validator.evaluation;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SizeEvaluationTest {
    
    private Map<Integer, String> generateMap() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "First");
        map.put(2, "Second");
        return map;
    }

    @Test
    public void evaluationTest() {
        assertTrue(new SizeEvaluation(2).evaluateAgainst(new Integer[] {1, 2}));
        assertTrue(new SizeEvaluation(2).evaluateAgainst(Arrays.asList(new Integer[] {1, 2})));
        assertTrue(new SizeEvaluation(2).evaluateAgainst(generateMap()));
        assertFalse(new SizeEvaluation(1).evaluateAgainst(new Integer[] {1, 2}));
        assertFalse(new SizeEvaluation(1).evaluateAgainst(Arrays.asList(new Integer[] {1, 2})));
        assertFalse(new SizeEvaluation(1).evaluateAgainst(generateMap()));
        assertFalse(new SizeEvaluation(1).evaluateAgainst("Alex"));
        assertFalse(new SizeEvaluation(1).evaluateAgainst(null));
    }
    
    @Test
    public void errerMessageTest() {
        assertEquals("Must be of size 4", new SizeEvaluation(4).getErrorMessage());
    }

}
