package io.ljunggren.validator.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import io.ljunggren.validator.Validator;
import io.ljunggren.validator.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

public class NotEmptyValidationTest {

    @AllArgsConstructor
    @Data
    private class NotEmptyPojo {
        @NotEmpty
        private String[] names;

        @NotEmpty
        private List<Integer> years;

        @NotEmpty
        private String document;

        @NotEmpty
        private Map<Integer, String> catalog;
    }

    private NotEmptyPojo pojo;

    @Before
    public void setup() {
        String[] names = { "Alex", "John", "Sarah" };
        List<Integer> years = Arrays.asList(new Integer[] { 2019, 2020, 2021 });
        Map<Integer, String> catalog = new HashMap<Integer, String>();
        catalog.put(1, "A");
        catalog.put(2, "B");
        pojo = new NotEmptyPojo(names, years, "Document", catalog);
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
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateInvalidYearsTest() {
        pojo.setYears(new ArrayList<Integer>());
        Validator validator = new Validator(pojo).validate();
        assertFalse(validator.isValid());
        assertEquals(1, validator.getInvalidItems().size());
        assertFalse(validator.getInvalidItems().get(0).getErrorMessages().isEmpty());
    }

    @Test
    public void validateInvalidDocumentTest() {
        pojo.setDocument("");
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
