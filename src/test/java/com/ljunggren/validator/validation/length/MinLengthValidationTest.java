package com.ljunggren.validator.validation.length;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ljunggren.validator.Validator;
import com.ljunggren.validator.annotation.length.MinLength;

import lombok.AllArgsConstructor;
import lombok.Data;

public class MinLengthValidationTest {
    
    @AllArgsConstructor
    @Data
    private class MinLengthPojo {
        @MinLength(4)
        private String name;
        
        @MinLength(5)
        private Integer zipCode;
    }
    
    private MinLengthPojo pojo;
    
    @Before
    public void setup() {
        pojo = new MinLengthPojo("Alex", 46123);
    }

    @Test
    public void validateTest() {
        Validator validator = new Validator(pojo).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }

    @Test
    public void validateInvalidNameTest() {
        pojo.setName("Al");
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateInvalidZipCodeTest() {
        pojo.setZipCode(4612);
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateNullNameTest() {
        pojo.setName(null);
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }
    
    @Test
    public void validateNullZipCodeTest() {
        pojo.setZipCode(null);
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }
    
}
