package com.ljunggren.validator.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ljunggren.validator.Validator;
import com.ljunggren.validator.annotation.EmailValidation;

import lombok.AllArgsConstructor;

public class EmailValidationChainTest {

    @AllArgsConstructor
    private class EmailPojo {
        @EmailValidation
        private String email;
    }

    @Test
    public void validateTest() {
        Validator validator = new Validator(new EmailPojo("alex@email.com")).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }

    @Test
    public void validateInvalidTest() {
        Validator validator = new Validator(new EmailPojo("alex.com")).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessage().isEmpty());
    }

}
