package io.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import io.ljunggren.validator.Item;
import io.ljunggren.validator.annotation.CustomValidator;
import io.ljunggren.validator.evaluation.Evaluation;

public class CustomValidation extends ValidationChain {
    
    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == CustomValidator.class) {
            Class<?> clazz = ((CustomValidator) annotation).value();
            try {
                Evaluation<Object> evaluation = instantiateEvaluation(clazz);
                if (!evaluation.isValid(item.getValue())) {
                    item.addErrorMessage(evaluation.getErrorMessage());
                }
            } catch (Exception e) {
                item.addErrorMessage(e.getMessage());
            }
        }
        nextChain.validate(annotation, item);
    }
    
    @SuppressWarnings("unchecked")
    private Evaluation<Object> instantiateEvaluation(Class<?> clazz) throws Exception {
        try {
            if (Evaluation.class.isAssignableFrom(clazz)) {
                return (Evaluation<Object>) clazz.newInstance();
            } 
            throw new Exception(String.format("CustomValidator(%s) does not implement Evaluation", clazz.getSimpleName()));
        } catch (ClassNotFoundException e) {
            throw new Exception("CustomValidator class not found " + e.getMessage());
        }
    }

}
