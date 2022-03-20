package com.yoon.t.specification;

public class AndCondition implements MatchCondition {

    private final MatchCondition left;
    private final MatchCondition right;

    public AndCondition(MatchCondition left, MatchCondition right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isSatisfy(Object factor) {
        return left.isSatisfy(factor) && right.isSatisfy(factor);
    }
}
