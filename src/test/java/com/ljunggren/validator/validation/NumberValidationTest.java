package com.ljunggren.validator.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ljunggren.validator.Validator;
import com.ljunggren.validator.annotation.Number;

import lombok.AllArgsConstructor;

public class NumberValidationTest {
    
    @AllArgsConstructor
    private class NumberPojo {
        @Number
        private String duration;
    }

    @Test
    public void validateTest() {
        Validator validator = new Validator(new NumberPojo("2")).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }

    @Test
    public void validateInvalidTest() {
        Validator validator = new Validator(new NumberPojo("Alex")).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
    }

    @Test
    public void validateNullTest() {
        Validator validator = new Validator(new NumberPojo(null)).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
    }

}
