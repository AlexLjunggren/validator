package com.ljunggren.validator.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ljunggren.validator.Validator;
import com.ljunggren.validator.annotation.AlphaNumeric;

import lombok.AllArgsConstructor;

public class CatchAllValidationTest {

    @AllArgsConstructor
    private class Pojo {
        @AlphaNumeric
        private int number;
    }

    @Test
    public void validateTest() {
        Validator validator = new Validator(new Pojo(1234)).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }

}
