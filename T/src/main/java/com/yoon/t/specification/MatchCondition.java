package com.yoon.t.specification;

import com.yoon.t.specification.utils.specs.MapCondition;

public interface MatchCondition {

    /** java.lang.StackOverflowError */
    static MatchCondition expected(String expectedContainedKey, String expectedContainedValue) {
        MatchCondition condition = MapCondition.expected(expectedContainedKey, expectedContainedValue);
        return new MapCondition(expectedContainedKey, expectedContainedValue);
    }

    boolean isSatisfy(Object o);
}
