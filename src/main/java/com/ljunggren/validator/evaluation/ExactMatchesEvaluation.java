package com.ljunggren.validator.evaluation;

import java.util.Arrays;

public class ExactMatchesEvaluation implements Evaluation {

	private String[] matches = {};
	
	public ExactMatchesEvaluation(String[] matches) {
		this.matches = matches;
	}

	@Override
	public boolean evaluateAgainst(String value) {
		if (value == null || matches == null) {
			return false;
		}
		return Arrays.stream(matches).filter(match -> value.equals(match))
				.findFirst()
				.orElse(null) != null;
	}

	@Override
	public String getErrorMessage() {
		return String.format("Value must be on of the following: %s", String.join(", ", matches));
	}

}
