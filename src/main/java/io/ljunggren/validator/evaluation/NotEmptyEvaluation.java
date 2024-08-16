package io.ljunggren.validator.evaluation;

import java.util.Collection;
import java.util.Map;

public class NotEmptyEvaluation implements Evaluation<Object> {

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return false;
        }
        return !isEmpty(value);
    }
    
    private boolean isEmpty(Object value) {
        if (value instanceof String) 
            return value.toString().isEmpty();
        if (value instanceof Collection)
            return ((Collection<?>) value).isEmpty();
        if (value instanceof Object[])
            return ((Object[]) value).length == 0;
        if (value instanceof Map)
            return ((Map<?, ?>) value).isEmpty();
        return false;
    }

}
