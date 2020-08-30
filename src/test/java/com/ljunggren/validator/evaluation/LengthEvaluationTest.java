package com.ljunggren.validator.evaluation;

import static org.junit.Assert.*;

import org.junit.Test;

public class LengthEvaluationTest {

	@Test
	public void evaluationTest() {
		assertTrue(new LengthEvaluation(4).evaluateAgainst("test"));
		assertTrue(new LengthEvaluation(0).evaluateAgainst(""));
		assertFalse(new LengthEvaluation(0).evaluateAgainst(" "));
		assertFalse(new LengthEvaluation(5).evaluateAgainst("test"));
		assertFalse(new LengthEvaluation(5).evaluateAgainst(null));
	}

	@Test
	public void errorMessageTest() {
		assertTrue(!new LengthEvaluation(4).getErrorMessage().isEmpty());
	}

}
