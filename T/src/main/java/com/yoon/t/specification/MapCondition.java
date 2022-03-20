package com.yoon.t.specification;

import java.util.Map;

public class MapCondition extends MatchCondition {

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
