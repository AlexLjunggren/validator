package io.ljunggren.validator.validation.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.ljunggren.validator.Validator;
import io.ljunggren.validator.annotation.math.Between;
import lombok.AllArgsConstructor;
import lombok.Data;

public class BetweenValidationTest {

    @AllArgsConstructor
    @Data
    private class BetweenPojo {
        @Between(minimum = 3, maximum = 4, message = "Must be between 3 and 4")
        private Double gpa;
    }

    @Test
    public void valiateTest() {
        Validator validator = new Validator(new BetweenPojo(3.6)).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }
    
    @Test
    public void validateInvalidTest() {
        Validator validator = new Validator(new BetweenPojo(4.0)).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateNullTest() {
        Validator validator = new Validator(new BetweenPojo(null)).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @AllArgsConstructor
    @Data
    private class BetweenInclusivePojo {
        @Between(minimum = 3, maximum = 4, inclusive = true, message = "Must be between 3 and 4 inclusive")
        private double gpa;
    }

    @Test
    public void valiateInclusiveTest() {
        Validator validator = new Validator(new BetweenInclusivePojo(4)).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }
    
    @Test
    public void validateInclusiveInvalidTest() {
        Validator validator = new Validator(new BetweenInclusivePojo(2.9)).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

}
