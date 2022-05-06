package com.specification.matchSpecs.specs;

public interface Condition {

    boolean isSatisfy(Object o);

    static Condition and(Condition right, Condition left) {
        return left.and(right);
    }

    static Condition or(Condition right, Condition left) {
        return left.and(right);
    }

    static Condition not(Condition condition) {
        return condition.not();
    }

    default Condition and(Condition right) {
        return new AndCondition(this, right);
    }

    default Condition or(Condition right) {
        return new OrCondition(this, right);
    }

    default Condition not() {
        return new NotCondition(this);
    }


}
