package com.ljunggren.validator.validation.math;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ljunggren.validator.Validator;
import com.ljunggren.validator.annotation.math.GreaterThanValidation;

import lombok.AllArgsConstructor;
import lombok.Data;

public class GreaterThanValidationChainTest {
    
    @AllArgsConstructor
    @Data
    private class GreaterThanPojo {
        @GreaterThanValidation(minimum = 3.2)
        private double gpa;
    }

    @Test
    public void valiateTest() {
        Validator validator = new Validator(new GreaterThanPojo(3.6)).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }
    
    @Test
    public void validateInvalidTest() {
        Validator validator = new Validator(new GreaterThanPojo(3.2)).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

}
