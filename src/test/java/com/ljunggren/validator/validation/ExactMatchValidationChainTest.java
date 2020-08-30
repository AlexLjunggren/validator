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
		private String name;
		
		@ExactMatchValidation(match = "46123")
		private int zipCode;
		
		@ExactMatchValidation(match = "100000")
		private long salary;
	}
	
	private ExactMatchPojo pojo;
	
	@Before
	public void setup() {
		pojo = new ExactMatchPojo("Alex", 46123, 100000);
	}

	@Test
	public void validateTest() {
		Validator validator = new Validator(pojo).validate();
		assertTrue(validator.isValid());
		assertEquals(0, validator.getInvalidItems().size());
	}

	@Test
	public void validateInvalidNameTest() {
		pojo.setName("alex");
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
