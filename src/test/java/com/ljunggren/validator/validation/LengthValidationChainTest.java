package com.ljunggren.validator.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ljunggren.validator.Validator;
import com.ljunggren.validator.annotation.LengthValidation;

import lombok.AllArgsConstructor;
import lombok.Data;

public class LengthValidationChainTest {

    @AllArgsConstructor
    @Data
    private class LengthPojo {
        @LengthValidation(length = 4)
        private String name;

        @LengthValidation(length = 5)
        private int zipCode;

        @LengthValidation(length = 6)
        private long salary;
    }

    private LengthPojo pojo;

    @Before
    public void setup() {
        pojo = new LengthPojo("Alex", 46123, 100000);
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
        assertFalse(validator.getInvalidItems().get(0).getErrorMessage().isEmpty());
    }

    @Test
    public void validateInvalidZipCodeTest() {
        pojo.setZipCode(4612);
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessage().isEmpty());
    }

    @Test
    public void validateInvalidSalaryTest() {
        pojo.setSalary(40000);
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessage().isEmpty());
    }

}
