package io.ljunggren.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.ljunggren.validator.annotation.Alpha;
import io.ljunggren.validator.annotation.ExactMatch;
import io.ljunggren.validator.annotation.NotNull;
import io.ljunggren.validator.annotation.length.Length;
import lombok.AllArgsConstructor;

public class StackedValidationTest {

    @AllArgsConstructor
    private class StackedPojo {
        @NotNull
        @Alpha
        @Length(value = 4, message = "Must be of length 4")
        @ExactMatch(match = "Alex", message = "Must match Alex")
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
        assertEquals(4, validator.getInvalidItems().get(0).getErrorMessages().size());
    }

}
