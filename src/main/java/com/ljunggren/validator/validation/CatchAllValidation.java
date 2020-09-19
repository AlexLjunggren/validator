package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;

public class CatchAllValidation extends ValidationChain {

    @Override
    public void validate(Annotation annotation, Item item) {
        return;
    }

}
