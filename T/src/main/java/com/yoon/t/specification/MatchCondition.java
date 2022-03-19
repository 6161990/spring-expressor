package com.yoon.t.specification;

import java.util.Map;

public class MatchCondition {
    public boolean isSatisfy(Object o){
        return (o instanceof Map)
                && ((Map<String, String>) o).containsKey("foo") || ((Map<String, String>) o).containsKey("bar");
    }
}
