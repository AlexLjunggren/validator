package com.ljunggren.validator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Item {

    private Object value;
    private Field field;
    private List<String> errorMessages = new ArrayList<String>();

    public Item(Object value, Field field) {
        this.value = value;
        this.field = field;
    }
    
    public void addErrorMessage(String errorMessage) {
        errorMessages.add(errorMessage);
    }

    public boolean isValid() {
        return errorMessages.isEmpty();
    }

    public String getMemberName() {
        return field == null ? null : field.getName();
    }

}
