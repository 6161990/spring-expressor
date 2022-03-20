package com.yoon.t.specification;

import java.util.Map;

public class MatchCondition {

    public static MatchCondition expected(String expectedContainedKey, String expectedContainedValue) {
        return new MapCondition(expectedContainedKey, expectedContainedValue);
    }

    public boolean isSatisfy(Object o){
        throw new UnsupportedOperationException();
    }
}
