package com.ljunggren.validator.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ljunggren.validator.Validator;
import com.ljunggren.validator.annotation.NotEmptyValidation;

import lombok.AllArgsConstructor;
import lombok.Data;

public class NotEmptyValidationChainTest {

    @AllArgsConstructor
    @Data
    private class NotEmptyPojo {
        @NotEmptyValidation
        private String[] names;

        @NotEmptyValidation
        private List<Integer> years;

        @NotEmptyValidation
        private String document;
    }

    private NotEmptyPojo pojo;

    @Before
    public void setup() {
        String[] names = { "Alex", "John", "Sarah" };
        List<Integer> years = Arrays.asList(new Integer[] { 2019, 2020, 2021 });
        pojo = new NotEmptyPojo(names, years, "Document");
    }

    @Test
    public void validateTest() {
        Validator validator = new Validator(pojo).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }

    @Test
    public void validateInvalidNamesTest() {
        pojo.setNames(new String[] {});
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessage().isEmpty());
    }

    @Test
    public void validateInvalidYearsTest() {
        pojo.setYears(new ArrayList<Integer>());
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessage().isEmpty());
    }

    @Test
    public void validateInvalidDocumentTest() {
        pojo.setDocument("");
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessage().isEmpty());
    }

}
