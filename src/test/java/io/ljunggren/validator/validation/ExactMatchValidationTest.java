package io.ljunggren.validator.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import io.ljunggren.validator.Validator;
import io.ljunggren.validator.annotation.ExactMatch;
import lombok.AllArgsConstructor;
import lombok.Data;

public class ExactMatchValidationTest {

    @AllArgsConstructor
    @Data
    private class ExactMatchPojo {
        @ExactMatch(match = "Alex", message = "Must be Alex")
        private String firstName;

        @ExactMatch(match = "Ljunggren", caseSensitive = false, message = "Must be Ljunggren")
        private String lastName;

        @ExactMatch(match = "46123", message = "Must be 46123")
        private int zipCode;

        @ExactMatch(match = "100000", message = "Must be 100000")
        private Long salary;

        @ExactMatch(matches = { "PST", "MST", "CST", "EST" }, message = "Must be one of the following: PST, MST, CST, EST")
        private String timeZone;

        @ExactMatch(matches = { "1999", "2020", "2021" }, message = "Must be one of the following: 1999, 2020, 2021")
        private int year;
    }

    private ExactMatchPojo pojo;

    @Before
    public void setup() {
        pojo = new ExactMatchPojo("Alex", "Ljunggren", 46123, 100000L, "EST", 2020);
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
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
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
        pojo.setSalary(40000L);
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateInvalidTimeZoneTest() {
        pojo.setTimeZone("GMT");
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateInvalidYearTest() {
        pojo.setYear(1998);
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateNullStringTest() {
        pojo.setFirstName(null);
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateNullNumberTest() {
        pojo.setSalary(null);
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

}
