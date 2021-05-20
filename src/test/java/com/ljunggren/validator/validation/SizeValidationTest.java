package com.ljunggren.validator.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.ljunggren.validator.Validator;
import com.ljunggren.validator.annotation.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

public class SizeValidationTest {
    
    @AllArgsConstructor
    @Data
    private class SizePojo {
        @Size(3)
        private String[] names;

        @Size(3)
        private List<Integer> years;

        @Size(2)
        private Map<Integer, String> catalog;
    }
    
    private SizePojo pojo;

    @Before
    public void setup() {
        String[] names = { "Alex", "John", "Sarah" };
        List<Integer> years = Arrays.asList(new Integer[] { 2019, 2020, 2021 });
        Map<Integer, String> catalog = new HashMap<Integer, String>();
        catalog.put(1, "A");
        catalog.put(2, "B");
        pojo = new SizePojo(names, years, catalog);
    }

    @Test
    public void validateTest() {
        Validator validator = new Validator(pojo).validate();
        assertTrue(validator.isValid());
        assertEquals(0, validator.getInvalidItems().size());
    }

    @Test
    public void validateInvalidNamesTest() {
        pojo.setNames(new String[] { "Alex", "John" });
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateInvalidYearsTest() {
        pojo.setYears(Arrays.asList(new Integer[] { 2019, 2020, 2021, 2022 }));
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateInvalidMapTest() {
        pojo.setCatalog(new HashMap<Integer, String>());
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateNullMapTest() {
        pojo.setCatalog(null);
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

}
