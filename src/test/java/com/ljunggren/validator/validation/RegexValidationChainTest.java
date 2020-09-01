package com.ljunggren.validator.validation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ljunggren.validator.Validator;
import com.ljunggren.validator.annotation.RegexValidation;

import lombok.AllArgsConstructor;

public class RegexValidationChainTest {
    
    @AllArgsConstructor
    private class RegexPojo {
        @RegexValidation(regex = "^[a-zA-Z]+$")
        private String name;
    }

    @Test
    public void validateTest() {
        Validator validator = new Validator(new RegexPojo("Alex")).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }

    @Test
    public void validateInvalidTest() {
        Validator validator = new Validator(new RegexPojo("1234")).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessage().isEmpty());
    }

}
