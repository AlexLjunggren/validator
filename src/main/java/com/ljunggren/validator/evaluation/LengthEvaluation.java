package com.ljunggren.validator.evaluation;

public class LengthEvaluation implements Evaluation {

	private int length;

	public LengthEvaluation(int length) {
		this.length = length;
	}

	@Override
	public boolean evaluateAgainst(String value) {
		return value == null ? false : value.length() == length;
	}

	@Override
	public String getErrorMessage() {
		return String.format("Must be of length %d", length);
	}

}
