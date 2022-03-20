package com.yoon.t.specification;

import java.util.Map;

public class PersonAgeAndNameCondition {

    public boolean isSatisfy(Map<String, Object> factor){
        return "foo".equals(factor.get("name")) && 18 == (int)factor.get("age");
    }
}
