package com.ljunggren.validator;

import java.lang.reflect.Field;

import lombok.Data;

@Data
public class Item {

	private Object value;
	private Field field;
	private String errorMessage;
	
	public Item(Object value, Field field) {
		this.value = value;
		this.field = field;
	}

	public boolean isValid() {
		return errorMessage == null || errorMessage.isEmpty();
	}

	public String getMemberName() {
		if (field != null) {
			return field.getName();
		}
		return null;
	}
	
}
