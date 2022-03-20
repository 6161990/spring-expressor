package com.yoon.t.specification.utils.specs;

public class AndCondition implements Condition {

    private final Condition left;
    private final Condition right;

    public AndCondition(Condition left, Condition right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isSatisfy(Object factor) {
        return left.isSatisfy(factor) && right.isSatisfy(factor);
    }
}
