package io.ljunggren.validator.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.ljunggren.validator.Validator;
import io.ljunggren.validator.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

public class NotNullValidationTest {

    @AllArgsConstructor
    @Data
    private class NotNullPojo {
        @NotNull
        private String name;
    }

    @Test
    public void validateTest() {
        Validator validator = new Validator(new NotNullPojo("Alex")).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }

    @Test
    public void validateInvalidTest() {
        Validator validator = new Validator(new NotNullPojo(null)).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertEquals("Cannot be null", validator.getInvalidItems().get(0).getErrorMessages().get(0));
    }

}
