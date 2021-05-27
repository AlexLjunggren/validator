package com.ljunggren.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.ljunggren.validator.annotation.Optional;
import com.ljunggren.validator.validation.AlphaNumericValidation;
import com.ljunggren.validator.validation.AlphaValidation;
import com.ljunggren.validator.validation.CatchAllValidation;
import com.ljunggren.validator.validation.CustomValidation;
import com.ljunggren.validator.validation.EmailValidation;
import com.ljunggren.validator.validation.ExactMatchValidation;
import com.ljunggren.validator.validation.DateFormatValidation;
import com.ljunggren.validator.validation.NotEmptyValidation;
import com.ljunggren.validator.validation.NotNullValidation;
import com.ljunggren.validator.validation.NumberValidation;
import com.ljunggren.validator.validation.NumericValidation;
import com.ljunggren.validator.validation.OptionalValidation;
import com.ljunggren.validator.validation.RegexValidation;
import com.ljunggren.validator.validation.SizeValidation;
import com.ljunggren.validator.validation.ValidationChain;
import com.ljunggren.validator.validation.length.LengthValidation;
import com.ljunggren.validator.validation.length.MaxLengthValidation;
import com.ljunggren.validator.validation.length.MinLengthValidation;
import com.ljunggren.validator.validation.math.BetweenValidation;
import com.ljunggren.validator.validation.math.GreaterThanOrEqualToValidation;
import com.ljunggren.validator.validation.math.GreaterThanValidation;
import com.ljunggren.validator.validation.math.LessThanOrEqualToValidation;
import com.ljunggren.validator.validation.math.LessThanValidation;
import com.ljunggren.validator.validation.math.NotBetweenValidation;
import com.ljunggren.validator.validation.string.ContainsValidation;
import com.ljunggren.validator.validation.string.EndsWithValidation;
import com.ljunggren.validator.validation.string.StartsWithValidation;

import lombok.Getter;

@Getter
public class Validator {

    private Object object;
    private List<Item> invalidItems = new ArrayList<>();

    public Validator(Object object) {
        this.object = object;
    }

    public Validator validate() {
        List<Item> items = findItems(object);
        validateItems(items);
        extractInvalidItems(items);
        return this;
    }
    
    public Validator template() {
        List<Item> items = findItems(object);
        templateItems(items);
        extractInvalidItems(items);
        return this;
    }
    
    public boolean isValid() {
        return invalidItems.isEmpty();
    }
    
    private void extractInvalidItems(List<Item> items) {
        items.stream().forEach(item -> {
            if (!item.isValid()) {
                invalidItems.add(item);
            }
        });
    }

    private List<Item> findItems(Object object) {
        List<Item> items = new ArrayList<Item>();
        List<Field> fields = findObjectFields(object);
        for (Field field : fields) {
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
        if (object != null) {
            Class<?> clazz = object.getClass();
            Field[] fields = FieldUtils.getAllFields(clazz);
            return Arrays.asList(fields);
        }
        return new ArrayList<Field>();
    }

    private void validateItems(List<Item> items) {
        items.forEach(item -> validateItem(item));
    }

    private void validateItem(Item item) {
        Annotation[] annotations = item.getField().getAnnotations();
        Arrays.asList(annotations).forEach(annotation -> {
            if (containsOptionalValidation(item) && item.getValue() == null) {
                new CatchAllValidation().validate(annotation, item);
                return;
            }
            getValidationChain(annotation, item).validate(annotation, item);
        });
    }
    
    private void templateItems(List<Item> items) {
        items.forEach(item -> {
            item.setValue(null);
            templateItem(item);
        });
    }

    private void templateItem(Item item) {
        Annotation[] annotations = item.getField().getAnnotations();
        Arrays.asList(annotations).forEach(annotation -> {
            new OptionalValidation()
                    .nextChain(getValidationChain(annotation, item))
                    .validate(annotation, item);
        });
    }

    private ValidationChain getValidationChain(Annotation annotation, Item item) {
        return new AlphaNumericValidation()
                .nextChain(new AlphaValidation()
                .nextChain(new CustomValidation()
                .nextChain(new EmailValidation()
                .nextChain(new ExactMatchValidation()
                .nextChain(new NotEmptyValidation()
                .nextChain(new NotNullValidation()
                .nextChain(new NumericValidation()
                .nextChain(new NumberValidation()
                .nextChain(new RegexValidation()
                .nextChain(new SizeValidation()
                .nextChain(new DateFormatValidation()
                .nextChain(lengthChain()
                        ))))))))))));
    }
    
    private ValidationChain lengthChain() {
        return new LengthValidation()
                .nextChain(new MaxLengthValidation()
                .nextChain(new MinLengthValidation()
                .nextChain(mathChain())));
    }
    
    private ValidationChain mathChain() {
        return new BetweenValidation()
                .nextChain(new GreaterThanOrEqualToValidation()
                .nextChain(new GreaterThanValidation()
                .nextChain(new LessThanOrEqualToValidation()
                .nextChain(new LessThanValidation()
                .nextChain(new NotBetweenValidation()
                .nextChain(stringChain()
                        ))))));
    }
    
    private ValidationChain stringChain() {
        return new ContainsValidation()
                .nextChain(new EndsWithValidation()
                .nextChain(new StartsWithValidation()
                .nextChain(new CatchAllValidation()
                        )));
    }
    
    private boolean containsOptionalValidation(Item item) {
        return item.getField().getAnnotation(Optional.class) != null;
    }

}
