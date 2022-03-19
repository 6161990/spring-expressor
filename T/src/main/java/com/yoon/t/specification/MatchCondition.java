package com.yoon.t.specification;

import java.util.Map;

public class MatchCondition {
    public boolean isSatisfy(Object o){
        Map<String, String> map = (Map<String, String>) o;
        return map.containsKey("foo") || map.containsKey("bar");
    }
}
