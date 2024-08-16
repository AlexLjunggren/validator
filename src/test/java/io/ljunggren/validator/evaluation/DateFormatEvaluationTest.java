package io.ljunggren.validator.evaluation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DateFormatEvaluationTest {

    @Test
    public void evaluationTest() {
        assertTrue(new DateFormatEvaluation("MM-dd-yyyy").isValid("05-04-2021"));
        assertTrue(new DateFormatEvaluation("MM-dd-yyyy").isValid("5-4-2021"));
        assertFalse(new DateFormatEvaluation("MM-dd-yyyy").isValid("13-04-2021"));
        assertFalse(new DateFormatEvaluation("MM-dd-yyyy").isValid("05-32-2021"));
        assertFalse(new DateFormatEvaluation("MM-dd-yyyy").isValid(""));
        assertFalse(new DateFormatEvaluation("MM-dd-yyyy").isValid(null));
    }
    
}
