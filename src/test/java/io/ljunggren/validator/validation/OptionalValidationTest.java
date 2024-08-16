package io.ljunggren.validator.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.ljunggren.validator.Validator;
import io.ljunggren.validator.annotation.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;

public class OptionalValidationTest {
    
    @AllArgsConstructor
    @Data
    private class OptionalPojo {
        @Optional
        private String name;
    }

    @Test
    public void validateTest() {
        Validator validator = new Validator(new OptionalPojo("Alex")).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }
    
}
