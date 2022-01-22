package io.ljunggren.validator.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.ljunggren.validator.Validator;
import io.ljunggren.validator.annotation.CustomValidator;
import io.ljunggren.validator.evaluation.AlphaEvaluation;
import lombok.AllArgsConstructor;

public class CustomValidationTest {
    
    @AllArgsConstructor
    private class CustomPojo {
        @CustomValidator(AlphaEvaluation.class)
        private String name;
    }
    
    @Test
    public void validateTest() {
        Validator validator = new Validator(new CustomPojo("Alex")).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
   }

    @Test
    public void validateInvalidTest() {
        Validator validator = new Validator(new CustomPojo("1234")).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
   }

    @AllArgsConstructor
    private class NonEvaluationPojo {
        @CustomValidator(String.class)
        private String name;
    }
    
    @Test
    public void validateNonEvaluationClassTest() {
        Validator validator = new Validator(new NonEvaluationPojo("Alex")).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertEquals("CustomValidator(String) does not implement Evaluation", validator.getInvalidItems().get(0).getErrorMessages().get(0));
   }
    
}
