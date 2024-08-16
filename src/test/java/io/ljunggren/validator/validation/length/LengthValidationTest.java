package io.ljunggren.validator.validation.length;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import io.ljunggren.validator.Validator;
import io.ljunggren.validator.annotation.length.Length;
import lombok.AllArgsConstructor;
import lombok.Data;

public class LengthValidationTest {

    @AllArgsConstructor
    @Data
    private class LengthPojo {
        @Length(value = 4, message = "Must be of length 4")
        private String name;

        @Length(value = 5, message = "Must be of length 5")
        private Integer zipCode;

        @Length(value = 6, message = "Must be of length 6")
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
    public void validateInvalidSalaryTest() {
        pojo.setSalary(40000);
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateNullSalaryTest() {
        pojo.setZipCode(null);
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

}
