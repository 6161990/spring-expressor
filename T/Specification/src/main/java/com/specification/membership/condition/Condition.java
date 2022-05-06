package com.specification.membership.condition;

public interface Condition<F> {
    Boolean isSatisfy(F factor);
}
