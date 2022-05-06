package com.specification.matchSpecs.specs;

import java.util.Map;

public class LessThanEqualsCondition implements Condition {

    private final String expectedKey;
    private final int value;

    public LessThanEqualsCondition(String expectedKey, int value) {
        this.expectedKey = expectedKey;
        this.value = value;
    }

    @Override
    public boolean isSatisfy(Object o) {
        if(o instanceof Map) {
            Map<String, Integer> map = (Map<String, Integer>) o;
            return map.get("age") < value;
        }
        return false;
    }

}
