package com.yoon.t.specification.utils.specs;

import com.yoon.t.specification.MatchCondition;

import java.util.Map;

public class MapCondition implements MatchCondition {

    /** java.lang.StackOverflowError */
    public static MapCondition expected(String expectedContainedKey, String expectedContainedValue) {
        MatchCondition condition = MapCondition.expected(expectedContainedKey, expectedContainedValue);
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
