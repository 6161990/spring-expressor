package com.specification;

import com.yoon.t.specification.utils.specs.Condition;

public interface MatchCondition extends Condition {

    boolean isSatisfy(Object o);
}