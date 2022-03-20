package com.yoon.t.specification;

public class PersonAgeAndNameCondition implements MatchCondition {

    private final MatchCondition condition1;
    private final MatchCondition condition2;

    public PersonAgeAndNameCondition(MatchCondition condition1, MatchCondition condition2) {
        this.condition1 = condition1;
        this.condition2 = condition2;
    }

    @Override
    public boolean isSatisfy(Object factor) {
        return condition1.isSatisfy(factor) && condition2.isSatisfy(factor);
    }
}
