package com.specification.matchSpecs;


import com.specification.matchSpecs.specs.Condition;

public interface MatchCondition extends Condition {

    boolean isSatisfy(Object o);
}
