package com.yoon.assertEnsure;

import org.valid4j.Assertive;

import java.util.Arrays;
import java.util.List;

import static org.valid4j.Validation.validate;

public class AssertEnsure {

    /***
     * validate(require) 는 exception
     *  ensure(assert) 는 error
     */
    public static List<Result> ensure(Allocatable allocatable, List<Result> results, String message) {
        validate(allocatable.getAmount() == 0, new IllegalArgumentException("UpSupport state"));
        long expected = Arrays.stream(allocatable.getMethods())
                .reduce(0L, (sum, method) -> sum + method.getAmount(), (integer, integer2) -> null);

        long total = results.stream().reduce(0L, (sum, method) -> sum + method.getAmount(), (integer, integer2) -> null);

        Assertive.ensure(expected == total, message); // 총합이 같지 않으면 ERROR 가 발생한다.
        return results;
    }

    public static class Allocatable {
        public int amount;

        public Method[] getMethods() {
            Method method = new Method(10);
            Method method1 = new Method(10);
            return new Method[]{method, method1};
        }

        public int getAmount() {
            return amount;
        }
    }


    public static class Method {
        public long amount;

        public Method(long i) {
            this.amount = i;
        }

        public long getAmount() {
            return amount;
        }

    }

    public static class Result {
        public int amount;
        public Method[] method;

        public Result(int amount, Method[] method) {
            this.amount = amount;
            this.method = method;
        }

        public int getAmount() {
            return amount;
        }

        public Method[] getMethod() {
            return method;
        }
    }
}

