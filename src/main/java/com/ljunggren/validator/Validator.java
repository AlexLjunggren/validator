package com.ljunggren.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.ljunggren.validator.annotation.OptionalValidation;
import com.ljunggren.validator.validation.AlphaNumericValidationChain;
import com.ljunggren.validator.validation.AlphaValidationChain;
import com.ljunggren.validator.validation.CatchAllValidationChain;
import com.ljunggren.validator.validation.EmailValidationChain;
import com.ljunggren.validator.validation.ExactMatchValidationChain;
import com.ljunggren.validator.validation.LengthValidationChain;

import lombok.Getter;

@Getter
public class Validator {
	
	private Object object;
	private List<Item> invalidItems = new ArrayList<>();
	private boolean valid = false;

	public Validator(Object object) {
		this.object = object;
	}
	
	public Validator validate() {
		List<Item> items = findItems(object);
		validateItems(items);
		items.stream().forEach(item -> {
			if (!item.isValid()) {
				invalidItems.add(item);
			}
		});
		valid = invalidItems.isEmpty();
		return this;
	}
	
	private List<Item> findItems(Object object) {
		List<Item> items = new ArrayList<Item>();
		List<Field> fields = findObjectFields(object);
		for (Field field: fields) {
			try {
				Object value = FieldUtils.readField(field, object, true);
				items.add(new Item(value, field));
			} catch (IllegalAccessException e) {
				items.add(new Item("Unknown Value", null));
			}
		}
		return items;
	}

	private List<Field> findObjectFields(Object object) {
		if (object != null ) {
			Class<?> clazz = object.getClass();
			Field[] fields = FieldUtils.getAllFields(clazz);
			return Arrays.asList(fields);
		}
		return new ArrayList<Field>();
	}
	
	public void validateItems(List<Item> items) {
		items.forEach(item -> validateItem(item));
	}
	
	private void validateItem(Item item) {
		Annotation[] annotations = item.getField().getAnnotations();
		Arrays.asList(annotations).forEach(annotation -> validationChain(annotation, item));
	}
	
	private void validationChain(Annotation annotation, Item item) {
		if (containsOptionalValidation(item) && item.getValue() == null) {
			new CatchAllValidationChain().validate(annotation, item);
			return;
		}
		new AlphaNumericValidationChain().nextChain(
				new AlphaValidationChain().nextChain(
				new EmailValidationChain().nextChain(
				new ExactMatchValidationChain().nextChain(
				new LengthValidationChain().nextChain(
				new CatchAllValidationChain()
						))))).validate(annotation, item);
	}
	
	private boolean containsOptionalValidation(Item item) {
		return item.getField().getAnnotation(OptionalValidation.class) != null;
	}

}
