package com.yoon;

import org.valid4j.Assertive;

import java.util.Arrays;

import static org.valid4j.Validation.validate;

public class AssertEnsure {

    /***
     * validate(require) 는 exception
     *  ensure(assert) 는 error
     */
    private static Result[] ensure(Allocatable allocatable, Result[] results, String message) {
        validate(allocatable.getAmount()==0, new IllegalArgumentException("UpSupport state"));
        long expected = Arrays.stream(allocatable.getMethods())
                .reduce(0L, (sum, method) -> sum + method.getAmount(), (integer, integer2) -> null);

        long total = Arrays.stream(results).reduce(0L, (sum, method) -> sum + method.getAmount(), (integer, integer2) -> null);

        Assertive.ensure(expected == total, message); // 총합이 같지 않으면 ERROR 가 발생한다.
        return results;
    }

    public static class Allocatable {
        public Method[] methods;
        public int amount;

        public Method[] getMethods() {
            Method method = new Method(10);
            Method method1 = new Method(20);
            return new Method[]{method, method1};
        }

        public int getAmount() {
            return amount;
        }
    }


    public  static class Method {
        public long amount;

        public Method(long i) {
            this.amount = i;
        }

        public long getAmount() {
            return amount;
        }

    }

    private static class Result {
        int amount;

        public int[] getMethods() {
            return new int[]{10};
        }

        public int getAmount() {
            return amount;
        }
    }
}

