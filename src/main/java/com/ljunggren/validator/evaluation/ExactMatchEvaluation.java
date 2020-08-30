package com.ljunggren.validator.evaluation;

public class ExactMatchEvaluation implements Evaluation {

	private String match = "";

	public ExactMatchEvaluation(String match) {
		this.match = match;
	}

	@Override
	public boolean evaluateAgainst(String value) {
		return value == null ? false : value.equals(match);
	}

	@Override
	public String getErrorMessage() {
		return String.format("Value must be '%s'", match);
	}

}
