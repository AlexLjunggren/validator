package com.ljunggren.validator.evaluation;

import static org.junit.Assert.*;

import org.junit.Test;

public class FormattedDateEvaluationTest {

    @Test
    public void evaluationTest() {
        assertTrue(new FormattedDateEvaluation("MM-dd-yyyy").isValid("05-04-2021"));
        assertTrue(new FormattedDateEvaluation("MM-dd-yyyy").isValid("5-4-2021"));
        assertFalse(new FormattedDateEvaluation("MM-dd-yyyy").isValid("13-04-2021"));
        assertFalse(new FormattedDateEvaluation("MM-dd-yyyy").isValid("05-32-2021"));
        assertFalse(new FormattedDateEvaluation("MM-dd-yyyy").isValid(""));
        assertFalse(new FormattedDateEvaluation("MM-dd-yyyy").isValid(null));
    }
    
    @Test
    public void errorMessageTest() {
        assertEquals("Must be in MM-dd-yyyy format", new FormattedDateEvaluation("MM-dd-yyyy").getErrorMessage());
    }

}
