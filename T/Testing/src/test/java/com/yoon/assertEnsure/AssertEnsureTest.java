package com.yoon.assertEnsure;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AssertEnsureTest {

    int amount = 10;

    @Test
    void ensureTest() {
        AssertEnsure.Allocatable allocatable = new AssertEnsure.Allocatable();

        AssertEnsure.Method[] methods = new AssertEnsure.Method[2];
        methods[0] =  new AssertEnsure.Method(amount);
        methods[1] =  new AssertEnsure.Method(amount);

        List<AssertEnsure.Result> results = new ArrayList<>();
        AssertEnsure.Result a = new AssertEnsure.Result(amount, methods);
        AssertEnsure.Result b = new AssertEnsure.Result(amount, methods);
        results.add(a);
        results.add(b);

        List<AssertEnsure.Result> sut = AssertEnsure.ensure(allocatable, results, "Not Equals");

        Assertions.assertThat(sut).containsExactly(a, b);
    }
}
