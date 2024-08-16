package io.ljunggren.validator.validation.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.ljunggren.validator.Validator;
import io.ljunggren.validator.annotation.string.Contains;
import lombok.AllArgsConstructor;
import lombok.Data;

public class ContainsValidationTest {
    
    @AllArgsConstructor
    @Data
    private class ContainsPojo {
        @Contains(text = "coded", message = "Must contain coded")
        private String note;
    }
    
    @Test
    public void validateTest() {
        Validator validator = new Validator(new ContainsPojo("Alex coded this")).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
        
    }

    @Test
    public void validateInvalidTest() {
        Validator validator = new Validator(new ContainsPojo("Alex programmed this")).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateNullTest() {
        Validator validator = new Validator(new ContainsPojo(null)).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

}
