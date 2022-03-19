package com.yoon.t.specification;

import java.util.Map;

public class MatchCondition {

    public static MatchCondition expectedContainedKey(String expectedContainedKey, String expectedContainedValue) {
        return new MatchCondition(expectedContainedKey, expectedContainedValue);
    }

    private String expectedContainedKey;
    private String expectedContainedValue;

    public MatchCondition(String expectedContainedKey, String expectedContainedValue) {
        this.expectedContainedKey = expectedContainedKey;
        this.expectedContainedValue = expectedContainedValue;
    }


    public boolean isSatisfy(Object o){
        if(o instanceof Map) {
            Map<String, String> map = (Map<String, String>) o;
            return expectedContainedValue.equals(map.get(expectedContainedKey));
        }
        return false;
    }
}
