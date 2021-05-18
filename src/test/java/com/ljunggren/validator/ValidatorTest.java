package com.ljunggren.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ljunggren.validator.annotation.AlphaNumeric;
import com.ljunggren.validator.annotation.Optional;

import lombok.AllArgsConstructor;

public class ValidatorTest {

    @AllArgsConstructor
    private class TestPojo {
        @Optional
        @AlphaNumeric
        private String name;
    }

    @Test
    public void validateTest() {
        Validator validator = new Validator(new TestPojo("Alex")).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }

    @Test
    public void validateNotValidTest() {
        Validator validator = new Validator(new TestPojo("@lex")).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
    }

    @Test
    public void validateOptionalTest() {
        Validator validator = new Validator(new TestPojo(null)).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }
    
    @Test
    public void templateTest() {
        Validator validator = new Validator(new TestPojo("Alex")).template();
        assertEquals(1, validator.getInvalidItems().size());
        assertEquals(2, validator.getInvalidItems().get(0).getErrorMessages().size());
    }

    @Test
    public void templateNullTest() {
        Validator validator = new Validator(new TestPojo(null)).template();
        assertEquals(1, validator.getInvalidItems().size());
        assertEquals(2, validator.getInvalidItems().get(0).getErrorMessages().size());
    }

}
