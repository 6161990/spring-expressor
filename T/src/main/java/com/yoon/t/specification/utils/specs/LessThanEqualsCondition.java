package com.yoon.t.specification.utils.specs;

import java.util.Map;

public class LessThanEqualsCondition implements Condition {

    private final String expectedKey;
    private final int value;

    public LessThanEqualsCondition(String expectedKey, int value) {
        this.expectedKey = expectedKey;
        this.value = value;
    }

    @Override
    public boolean isSatisfy(Object factor) {
        if(factor instanceof Map) {
            Map<String, String> map = (Map<String, String>) factor;
            String key = map.get("key");
        }
        return false;
    }
}
