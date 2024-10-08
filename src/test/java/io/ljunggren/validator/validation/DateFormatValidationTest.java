package io.ljunggren.validator.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import io.ljunggren.validator.Validator;
import io.ljunggren.validator.annotation.DateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

public class DateFormatValidationTest {

    @AllArgsConstructor
    @Data
    private class DatePojo {
        @DateFormat(format = "yyyy-MM-dd", message = "Must be in yyyy-MM-dd format")
        private String submittedDate;
    }
    
    private DatePojo pojo;
    
    @Before
    public void setup() {
        pojo = new DatePojo("2021-05-04");
    }

    @Test
    public void validateTest() {
        Validator validator = new Validator(pojo).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }
    
    @Test
    public void validateInvalidTest() {
        pojo.setSubmittedDate("2021-13-04");
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateNullTest() {
        pojo.setSubmittedDate(null);
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

}
