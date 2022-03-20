package com.yoon.t.specification.utils.specs;

public class NotCondition implements Condition {

    public NotCondition(Condition condition) {
    }

    @Override
    public boolean isSatisfy(Object o) {
        return false;
    }
}
