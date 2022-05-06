package com.specification.matchSpecs.specs;

public class NotCondition implements Condition {

    public NotCondition(Condition condition) {
    }

    @Override
    public boolean isSatisfy(Object o) {
        return false;
    }
}
