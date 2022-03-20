package com.yoon.t.specification;

public interface MatchCondition {

    static MatchCondition expected(String expectedContainedKey, String expectedContainedValue) {
        return new MapCondition(expectedContainedKey, expectedContainedValue);
    }

    boolean isSatisfy(Object o);
}
