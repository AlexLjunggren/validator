package com.ljunggren.validator.validation;

import java.lang.annotation.Annotation;

import com.ljunggren.validator.Item;
import com.ljunggren.validator.annotation.Custom;
import com.ljunggren.validator.evaluation.Evaluation;

public class CustomValidation extends ValidationChain {
    
    private Class<?> annotationClass = Custom.class;

    @Override
    public void validate(Annotation annotation, Item item) {
        if (annotation.annotationType() == annotationClass) {
            String className = ((Custom) annotation).className();
            try {
                Evaluation<Object> evaluation = instantiateEvaluation(className);
                if (!evaluation.isValid(item.getValue())) {
                    item.addErrorMessage(evaluation.getErrorMessage());
                    return;
                }
            } catch (Exception e) {
                item.addErrorMessage(e.getMessage());
                return;
            }
        }
        nextChain.validate(annotation, item);
    }
    
    @SuppressWarnings("unchecked")
    public Evaluation<Object> instantiateEvaluation(String className) throws Exception {
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
