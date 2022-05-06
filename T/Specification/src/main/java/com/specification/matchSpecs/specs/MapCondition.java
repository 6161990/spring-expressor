package com.specification.matchSpecs.specs;

import java.util.Map;

public class MapCondition implements Condition {

    public static Condition expected(String expectedContainedKey, String expectedContainedValue) {
        return new MapCondition(expectedContainedKey, expectedContainedValue);
    }

    private final String expectedContainedKey;
    private final String expectedContainedValue;

    public MapCondition(String expectedContainedKey, String expectedContainedValue) {
        this.expectedContainedKey = expectedContainedKey;
        this.expectedContainedValue = expectedContainedValue;
    }

    @Override
    public boolean isSatisfy(Object o){
        if(o instanceof Map) {
            Map<String, String> map = (Map<String, String>) o;
            return expectedContainedValue.equals(map.get(expectedContainedKey));
        }
        return false;
    }
}
