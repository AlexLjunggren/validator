package com.ljunggren.validator.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ljunggren.validator.Validator;
import com.ljunggren.validator.annotation.Numeric;

import lombok.AllArgsConstructor;

public class NumericValidationTest {
    
    @AllArgsConstructor
    private class NumericPojo {
        @Numeric
        private String amount;
    }

    @Test
    public void validateTest() {
        Validator validator = new Validator(new NumericPojo("2")).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }

    @Test
    public void validateInvalidTest() {
        Validator validator = new Validator(new NumericPojo("a")).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
    }

    @Test
    public void validateNullTest() {
        Validator validator = new Validator(new NumericPojo(null)).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
    }

}
