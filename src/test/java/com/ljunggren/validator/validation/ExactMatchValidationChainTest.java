package com.ljunggren.validator.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ljunggren.validator.Validator;
import com.ljunggren.validator.annotation.ExactMatchValidation;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ExactMatchValidationChainTest {

    @AllArgsConstructor
    @Data
    private class ExactMatchPojo {
        @ExactMatchValidation(match = "Alex")
        private String firstName;

        @ExactMatchValidation(match = "Ljunggren", caseSensitive = false)
        private String lastName;

        @ExactMatchValidation(match = "46123")
        private int zipCode;

        @ExactMatchValidation(match = "100000")
        private long salary;

        @ExactMatchValidation(matches = { "PST", "MST", "CST", "EST" })
        private String timeZone;

        @ExactMatchValidation(matches = { "1999", "2020", "2021" })
        private int year;
    }

    private ExactMatchPojo pojo;

    @Before
    public void setup() {
        pojo = new ExactMatchPojo("Alex", "Ljunggren", 46123, 100000, "EST", 2020);
    }

    @Test
    public void validateTest() {
        Validator validator = new Validator(pojo).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }

    @Test
    public void validateInvalidFirstNameTest() {
        pojo.setFirstName("alex");
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessage().isEmpty());
    }

    @Test
    public void validateLastNameTest() {
        pojo.setLastName("ljunggren");
        Validator validator = new Validator(pojo).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }

    @Test
    public void validateInvalidLastNameTest() {
        pojo.setLastName("noname");
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

    @Test
    public void validateInvalidTimeZoneTest() {
        pojo.setTimeZone("GMT");
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessage().isEmpty());
    }

    @Test
    public void validateInvalidYearTest() {
        pojo.setYear(1998);
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessage().isEmpty());
    }

    @AllArgsConstructor
    @Data
    private class ExactMatchNoArgsPojo {
        @ExactMatchValidation()
        private String name;

        @ExactMatchValidation()
        private String timeZone;
    }

    @Test
    public void validateNoArgsInvalidTest() {
        ExactMatchNoArgsPojo pojo = new ExactMatchNoArgsPojo("Alex", "EST");
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
    }

    @Test
    public void validateNoArgsTest() {
        ExactMatchNoArgsPojo pojo = new ExactMatchNoArgsPojo("", "");
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
    }

}
