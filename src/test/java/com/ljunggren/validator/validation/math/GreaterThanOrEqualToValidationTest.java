package com.ljunggren.validator.validation.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ljunggren.validator.Validator;
import com.ljunggren.validator.annotation.math.GreaterThanOrEqualTo;

import lombok.AllArgsConstructor;
import lombok.Data;

public class GreaterThanOrEqualToValidationTest {

    @AllArgsConstructor
    @Data
    private class GreaterThanOrEqualToPojo {
        @GreaterThanOrEqualTo(minimum = 3.2)
        private double gpa;
    }

    @Test
    public void valiateTest() {
        Validator validator = new Validator(new GreaterThanOrEqualToPojo(3.2)).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }
    
    @Test
    public void validateInvalidTest() {
        Validator validator = new Validator(new GreaterThanOrEqualToPojo(3.1)).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

}
