package com.yoon.t.specification;

import java.util.Map;

import static com.yoon.t.specification.MatchCondition.*;

public class PersonAgeAndNameCondition {

    private final MatchCondition condition1;
    private final MatchCondition condition2;

    public PersonAgeAndNameCondition(MatchCondition condition1, MatchCondition condition2) {

        this.condition1 = condition1;
        this.condition2 = condition2;
    }

    public boolean isSatisfy(Map<String, Object> factor){
        return expected("name", "foo").isSatisfy(factor)
                && expected("age", "18").isSatisfy(factor);
    }
}
