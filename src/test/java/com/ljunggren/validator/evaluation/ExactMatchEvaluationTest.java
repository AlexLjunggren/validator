package com.ljunggren.validator.evaluation;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExactMatchEvaluationTest {

	@Test
	public void evaluationTest() {
		assertTrue(new ExactMatchEvaluation("test").evaluateAgainst("test"));
		assertTrue(new ExactMatchEvaluation("1234").evaluateAgainst("1234"));
		assertTrue(new ExactMatchEvaluation("").evaluateAgainst(""));
		assertFalse(new ExactMatchEvaluation("test").evaluateAgainst("1234"));
		assertFalse(new ExactMatchEvaluation("test").evaluateAgainst(null));
		assertFalse(new ExactMatchEvaluation(null).evaluateAgainst("test"));
	}

	@Test
	public void errorMessageTest() {
		assertTrue(!new ExactMatchEvaluation("test").getErrorMessage().isEmpty());
	}

}
