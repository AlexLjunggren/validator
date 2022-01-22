package io.ljunggren.validator.validation.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.ljunggren.validator.Validator;
import io.ljunggren.validator.annotation.math.GreaterThan;
import lombok.AllArgsConstructor;
import lombok.Data;

public class GreaterThanValidationTest {
    
    @AllArgsConstructor
    @Data
    private class GreaterThanPojo {
        @GreaterThan(3.2)
        private Double gpa;
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

    @Test
    public void validateNullTest() {
        Validator validator = new Validator(new GreaterThanPojo(null)).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

}
