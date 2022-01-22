package io.ljunggren.validator.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.ljunggren.validator.Validator;
import io.ljunggren.validator.annotation.AlphaNumeric;
import lombok.AllArgsConstructor;

public class AlphaNumericValidationTest {

    @AllArgsConstructor
    private class AlphaNumericPojo {
        @AlphaNumeric
        private String name;
    }

    @Test
    public void validateTest() {
        Validator validator = new Validator(new AlphaNumericPojo("Alex1234")).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }

    @Test
    public void validateInvalidTest() {
        Validator validator = new Validator(new AlphaNumericPojo("@lex1234")).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
        assertEquals("name", validator.getInvalidItems().get(0).getMemberName());
    }

    @Test
    public void validateNullTest() {
        Validator validator = new Validator(new AlphaNumericPojo(null)).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
        assertEquals("name", validator.getInvalidItems().get(0).getMemberName());
    }

}
