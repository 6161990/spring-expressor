package com.yoon.t.specification;

import java.util.Map;

public class MatchCondition {
    private final String expectedContainedKey;

    public MatchCondition(String expectedContainedKey) {
        this.expectedContainedKey = expectedContainedKey;
    }

    public static MatchCondition expectedContainedKey(String expectedContainedKey) {
        return new MatchCondition(expectedContainedKey);
    }

    public boolean isSatisfy(Object o){
        if(o instanceof Map) {
            Map<String, String> map = (Map<String, String>) o;
           return map.containsKey(expectedContainedKey);
        }
        return false;
    }
}
