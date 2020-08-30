package com.ljunggren.validator.evaluation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ExactMatchesEvaluationTest {
	
	private String[] timeZones = {"PST", "MST", "CST", "EST"};

	@Test
	public void test() {
		assertTrue(new ExactMatchesEvaluation(timeZones).evaluateAgainst("MST"));
		assertFalse(new ExactMatchesEvaluation(timeZones).evaluateAgainst("GMT"));
		assertFalse(new ExactMatchesEvaluation(timeZones).evaluateAgainst(""));
		assertFalse(new ExactMatchesEvaluation(timeZones).evaluateAgainst(null));
		assertFalse(new ExactMatchesEvaluation(null).evaluateAgainst("MST"));
	}

	@Test
	public void errorMessageTest() {
		assertTrue(!new ExactMatchesEvaluation(timeZones).getErrorMessage().isEmpty());
	}
}
