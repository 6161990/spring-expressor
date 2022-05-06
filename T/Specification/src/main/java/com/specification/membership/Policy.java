package com.specification.membership;

import java.util.function.Function;

public interface Policy<F, R> {

    Function<F, R> outer();

    default R resultBy(F factor) {
        return outer().apply(factor);
    }

    boolean isSatisfy(F factor);
}
