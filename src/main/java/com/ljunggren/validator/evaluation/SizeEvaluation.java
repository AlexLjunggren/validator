package com.ljunggren.validator.evaluation;

import java.util.Collection;
import java.util.Map;

public class SizeEvaluation implements Evaluation<Object> {
    
    private int size;

    public SizeEvaluation(int size) {
        this.size = size;
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return false;
        }
        return size == getSize(value);
    }
    
    private int getSize(Object value) {
        if (value instanceof Collection)
            return ((Collection<?>) value).size();
        if (value instanceof Object[])
            return ((Object[]) value).length;
        if (value instanceof Map)
            return ((Map<?, ?>) value).size();
        return 0;
    }

    @Override
    public String getErrorMessage() {
        return String.format("Must be of size %d", size);
    }

}
