package com.ljunggren.validator.validation.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ljunggren.validator.Validator;
import com.ljunggren.validator.annotation.string.ContainsValidation;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ContainsValidationChainTest {
    
    @AllArgsConstructor
    @Data
    private class ContainsPojo {
        @ContainsValidation(text = "coded")
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

}
