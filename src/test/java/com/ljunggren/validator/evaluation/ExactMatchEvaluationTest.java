package com.ljunggren.validator.evaluation;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExactMatchEvaluationTest {

	@Test
	public void evaluationTest() {
		assertTrue(new ExactMatchEvaluation("test", true).evaluateAgainst("test"));
		assertTrue(new ExactMatchEvaluation("1234", true).evaluateAgainst("1234"));
		assertTrue(new ExactMatchEvaluation("", true).evaluateAgainst(""));
		assertFalse(new ExactMatchEvaluation("test", true).evaluateAgainst("1234"));
		assertFalse(new ExactMatchEvaluation("test", true).evaluateAgainst(null));
		assertFalse(new ExactMatchEvaluation(null, true).evaluateAgainst("test"));
	}

	@Test
	public void evaluationInsensitiveTest() {
		assertTrue(new ExactMatchEvaluation("test", false).evaluateAgainst("TEST"));
		assertTrue(new ExactMatchEvaluation("1234", false).evaluateAgainst("1234"));
		assertTrue(new ExactMatchEvaluation("", false).evaluateAgainst(""));
		assertFalse(new ExactMatchEvaluation("test", false).evaluateAgainst("1234"));
		assertFalse(new ExactMatchEvaluation("test", false).evaluateAgainst(null));
		assertFalse(new ExactMatchEvaluation(null, false).evaluateAgainst("test"));
	}

	@Test
	public void errorMessageTest() {
		assertTrue(!new ExactMatchEvaluation("test", true).getErrorMessage().isEmpty());
	}

}
