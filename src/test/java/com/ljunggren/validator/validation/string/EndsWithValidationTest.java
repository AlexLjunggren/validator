package com.ljunggren.validator.validation.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ljunggren.validator.Validator;
import com.ljunggren.validator.annotation.string.EndsWith;

import lombok.AllArgsConstructor;
import lombok.Data;

public class EndsWithValidationTest {
    
    @AllArgsConstructor
    @Data
    private class EndsWithPojo {
        @EndsWith(endText = "Alex")
        private String note;
    }

    @Test
    public void validateTest() {
        Validator validator = new Validator(new EndsWithPojo("This was coded by Alex")).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }

    @Test
    public void validateInvalidTest() {
        Validator validator = new Validator(new EndsWithPojo("Alex coded this")).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

}
