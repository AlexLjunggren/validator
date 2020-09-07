package com.ljunggren.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ljunggren.validator.annotation.AlphaValidation;
import com.ljunggren.validator.annotation.ExactMatchValidation;
import com.ljunggren.validator.annotation.LengthValidation;
import com.ljunggren.validator.annotation.NotNullValidation;

import lombok.AllArgsConstructor;

public class StackedValidationTest {

    @AllArgsConstructor
    private class StackedPojo {
        @NotNullValidation
        @AlphaValidation
        @LengthValidation(length = 4)
        @ExactMatchValidation(match = "Alex")
        private String name;
    }
    
    @Test
    public void validateTest() {
        StackedPojo pojo = new StackedPojo("Alex");
        Validator validator = new Validator(pojo).validate();
        assertTrue(validator.isValid());
    }
    
    @Test
    public void validateInvalidExactMatchTest() {
        StackedPojo pojo = new StackedPojo("alex");
        Validator validator = new Validator(pojo).validate();
        assertEquals(1, validator.getInvalidItems().get(0).getErrorMessages().size());
    }

    @Test
    public void validateInvalidTest() {
        StackedPojo pojo = new StackedPojo("Alex1");
        Validator validator = new Validator(pojo).validate();
        assertEquals(3, validator.getInvalidItems().get(0).getErrorMessages().size());
    }

    @Test
    public void validateInvalidNullTest() {
        StackedPojo pojo = new StackedPojo(null);
        Validator validator = new Validator(pojo).validate();
        assertEquals(1, validator.getInvalidItems().get(0).getErrorMessages().size());
    }

}
