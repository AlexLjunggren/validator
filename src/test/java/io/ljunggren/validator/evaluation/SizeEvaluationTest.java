package io.ljunggren.validator.evaluation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        assertTrue(new SizeEvaluation(2).isValid(new Integer[] {1, 2}));
        assertTrue(new SizeEvaluation(2).isValid(Arrays.asList(new Integer[] {1, 2})));
        assertTrue(new SizeEvaluation(2).isValid(generateMap()));
        assertFalse(new SizeEvaluation(1).isValid(new Integer[] {1, 2}));
        assertFalse(new SizeEvaluation(1).isValid(Arrays.asList(new Integer[] {1, 2})));
        assertFalse(new SizeEvaluation(1).isValid(generateMap()));
        assertFalse(new SizeEvaluation(1).isValid("Alex"));
        assertFalse(new SizeEvaluation(1).isValid(null));
    }
    
    @Test
    public void errerMessageTest() {
        assertEquals("Must be of size 4", new SizeEvaluation(4).getErrorMessage());
    }

}
