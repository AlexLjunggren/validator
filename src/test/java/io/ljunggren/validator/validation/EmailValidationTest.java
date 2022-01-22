package io.ljunggren.validator.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.ljunggren.validator.Validator;
import io.ljunggren.validator.annotation.Email;
import lombok.AllArgsConstructor;

public class EmailValidationTest {

    @AllArgsConstructor
    private class EmailPojo {
        @Email
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
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateNullTest() {
        Validator validator = new Validator(new EmailPojo(null)).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

}
