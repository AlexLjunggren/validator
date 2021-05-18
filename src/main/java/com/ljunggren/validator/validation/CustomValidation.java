package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.CustomValidator;
import com.ljunggren.validator.evaluation.Evaluation;

public class CustomValidation extends ValidationChain {
    
    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == CustomValidator.class) {
            String className = ((CustomValidator) annotation).className();
            try {
                Evaluation<Object> evaluation = instantiateEvaluation(className);
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
    private Evaluation<Object> instantiateEvaluation(String className) throws Exception {
        try {
            Class<?> clazz = Class.forName(className);
            if (Evaluation.class.isAssignableFrom(clazz)) {
                return (Evaluation<Object>) clazz.newInstance();
            } 
            throw new Exception("CustomValidator className does not implement Evaluation");
        } catch (ClassNotFoundException e) {
            throw new Exception("CustomValidator class not found " + e.getMessage());
        }
    }

}
