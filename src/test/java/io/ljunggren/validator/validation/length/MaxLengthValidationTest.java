package io.ljunggren.validator.validation.length;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import io.ljunggren.validator.Validator;
import io.ljunggren.validator.annotation.length.MaxLength;
import lombok.AllArgsConstructor;
import lombok.Data;

public class MaxLengthValidationTest {
    
    @AllArgsConstructor
    @Data
    private class MaxLengthPojo {
        @MaxLength(4)
        private String name;
        
        @MaxLength(5)
        private Integer zipCode;
    }
    
    private MaxLengthPojo pojo;

    @Before
    public void setup() {
        pojo = new MaxLengthPojo("Alex", 46123);
    }
    
    
    @Test
    public void validateTest() {
        Validator validator = new Validator(pojo).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }
    
    @Test
    public void validateInvalidNameTest() {
        pojo.setName("Alexander");
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateInvalidZipCodeTest() {
        pojo.setZipCode(461230);
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateNullNameTest() {
        pojo.setName(null);
        Validator validator = new Validator(pojo).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }
    
    @Test
    public void validateNullZipCodeTest() {
        pojo.setZipCode(null);
        Validator validator = new Validator(pojo).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }
    
}
