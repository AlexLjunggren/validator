package io.ljunggren.validator.validation.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.ljunggren.validator.Validator;
import io.ljunggren.validator.annotation.string.StartsWith;
import lombok.AllArgsConstructor;
import lombok.Data;

public class StartsWithValidationTest {
    
    @AllArgsConstructor
    @Data
    private class StartsWithPojo {
        @StartsWith(text = "Alex")
        private String note;
    }

    @Test
    public void validateTest() {
        Validator validator = new Validator(new StartsWithPojo("Alex coded this")).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }

    @Test
    public void validateInvalidTest() {
        Validator validator = new Validator(new StartsWithPojo("This was coded by Alex")).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateNullTest() {
        Validator validator = new Validator(new StartsWithPojo(null)).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

}
