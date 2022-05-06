package com.specification.matchSpecs.specs;

public class OrCondition implements Condition{

    private final Condition left;
    private final Condition right;

    public OrCondition(Condition left, Condition right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isSatisfy(Object factor) {
        return left.isSatisfy(factor) || right.isSatisfy(factor);
    }
}
