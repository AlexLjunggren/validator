package com.ljunggren.validator.validation.string;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ljunggren.validator.Validator;
import com.ljunggren.validator.annotation.string.StartsWithValidation;

import lombok.AllArgsConstructor;
import lombok.Data;

public class StartsWithValidationChainTest {
    
    @AllArgsConstructor
    @Data
    private class StartsWithPojo {
        @StartsWithValidation(startText = "Alex")
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

}
