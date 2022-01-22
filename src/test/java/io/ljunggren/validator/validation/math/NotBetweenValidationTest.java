package io.ljunggren.validator.validation.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.ljunggren.validator.Validator;
import io.ljunggren.validator.annotation.math.NotBetween;
import lombok.AllArgsConstructor;
import lombok.Data;

public class NotBetweenValidationTest {

    @AllArgsConstructor
    @Data
    private class NotBetweenPojo {
        @NotBetween(minimum = 3, maximum = 4)
        private Double gpa;
    }

    @Test
    public void valiateTest() {
        Validator validator = new Validator(new NotBetweenPojo(2.0)).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }
    
    @Test
    public void validateInvalidTest() {
        Validator validator = new Validator(new NotBetweenPojo(3.5)).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateNullTest() {
        Validator validator = new Validator(new NotBetweenPojo(null)).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @AllArgsConstructor
    @Data
    private class NotBetweenInclusivePojo {
        @NotBetween(minimum = 3, maximum = 4, inclusive = true)
        private double gpa;
    }

    @Test
    public void valiateInclusiveTest() {
        Validator validator = new Validator(new NotBetweenInclusivePojo(2)).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }
    
    @Test
    public void validateInclusiveInvalidTest() {
        Validator validator = new Validator(new NotBetweenInclusivePojo(3)).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

}
