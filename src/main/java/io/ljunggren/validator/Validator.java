package io.ljunggren.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.reflect.FieldUtils;

import io.ljunggren.validator.annotation.Optional;
import io.ljunggren.validator.validation.AlphaNumericValidation;
import io.ljunggren.validator.validation.AlphaValidation;
import io.ljunggren.validator.validation.CatchAllValidation;
import io.ljunggren.validator.validation.CustomValidation;
import io.ljunggren.validator.validation.DateFormatValidation;
import io.ljunggren.validator.validation.EmailValidation;
import io.ljunggren.validator.validation.ExactMatchValidation;
import io.ljunggren.validator.validation.NotEmptyValidation;
import io.ljunggren.validator.validation.NotNullValidation;
import io.ljunggren.validator.validation.NumberValidation;
import io.ljunggren.validator.validation.NumericValidation;
import io.ljunggren.validator.validation.OptionalValidation;
import io.ljunggren.validator.validation.RegexValidation;
import io.ljunggren.validator.validation.SizeValidation;
import io.ljunggren.validator.validation.ValidationChain;
import io.ljunggren.validator.validation.length.LengthValidation;
import io.ljunggren.validator.validation.length.MaxLengthValidation;
import io.ljunggren.validator.validation.length.MinLengthValidation;
import io.ljunggren.validator.validation.math.BetweenValidation;
import io.ljunggren.validator.validation.math.GreaterThanOrEqualToValidation;
import io.ljunggren.validator.validation.math.GreaterThanValidation;
import io.ljunggren.validator.validation.math.LessThanOrEqualToValidation;
import io.ljunggren.validator.validation.math.LessThanValidation;
import io.ljunggren.validator.validation.math.NotBetweenValidation;
import io.ljunggren.validator.validation.string.ContainsValidation;
import io.ljunggren.validator.validation.string.EndsWithValidation;
import io.ljunggren.validator.validation.string.StartsWithValidation;
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
    
    public Set<String> getErrorMessages() {
        return invalidItems.stream()
                .map(error -> error.getErrorMessages())
                .flatMap(message -> message.stream())
                .collect(Collectors.toSet());
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
            return FieldUtils.getAllFieldsList(clazz);
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
