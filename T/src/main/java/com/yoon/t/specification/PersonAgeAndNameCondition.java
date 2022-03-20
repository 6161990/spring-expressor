package com.yoon.t.specification;

import java.util.Map;

import static com.yoon.t.specification.MatchCondition.*;

public class PersonAgeAndNameCondition {

    public boolean isSatisfy(Map<String, Object> factor){
        return expected("name", "foo").isSatisfy(factor)
                && expected("age", "18").isSatisfy(factor);
    }
}
