package com.yoon.t.specification;

import java.util.Map;

public class MatchCondition {
    private final String expectedKey;

    public MatchCondition(String expectedKey) {
        this.expectedKey = expectedKey;
    }

    public boolean isSatisfy(Object o){
        if(o instanceof Map) {
            Map<String, String> map = (Map<String, String>) o;
           return map.containsKey("foo");
        }
        return false;
    }
}
