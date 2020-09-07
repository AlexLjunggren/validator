package com.ljunggren.validator.validation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ljunggren.validator.Validator;
import com.ljunggren.validator.annotation.CustomValidation;

import lombok.AllArgsConstructor;

public class CustomValidationChainTest {
    
    @AllArgsConstructor
    private class CustomPojo {
        @CustomValidation(className = "com.ljunggren.validator.evaluation.AlphaEvaluation")
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
        @CustomValidation(className = "com.ljunggren.validator.validation.AlphaValidationChain")
        private String name;
    }
    
    @Test
    public void validateNonEvaluationClassTest() {
        Validator validator = new Validator(new NonEvaluationPojo("Alex")).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertEquals("CustomValidator className does not implement Evaluation", validator.getInvalidItems().get(0).getErrorMessages().get(0));
   }
    
    @AllArgsConstructor
    private class InvalidClassPojo {
        @CustomValidation(className = "com.ljunggren.validator.evaluation.NotRealClass")
        private String name;
    }
    
    @Test
    public void validateInvalidClassTest() {
        Validator validator = new Validator(new InvalidClassPojo("Alex")).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertEquals("CustomValidator class not found com.ljunggren.validator.evaluation.NotRealClass", validator.getInvalidItems().get(0).getErrorMessages().get(0));
    }
    
}
