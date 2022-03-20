package com.yoon.t.specification;

import com.yoon.t.specification.utils.specs.Condition;
import com.yoon.t.specification.utils.specs.MapCondition;

public interface MatchCondition extends Condition{

    static MatchCondition expected(String expectedContainedKey, String expectedContainedValue) {
        return new MapCondition(expectedContainedKey, expectedContainedValue);
    }

    boolean isSatisfy(Object o);
}
