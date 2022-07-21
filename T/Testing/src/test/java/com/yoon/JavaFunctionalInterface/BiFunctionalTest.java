package com.yoon.JavaFunctionalInterface;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class BiFunctionalTest {

    @Test
    void BiFunctionTest() {
        BiFunction<String, String, String> func1 = (s1, s2) -> {
            String s3 = s1 + s2;
            return s3;
        };

        String result = func1.apply("Hello", "World");

        assertThat(result).isEqualTo("HelloWorld");

        BiFunction<Integer, Integer, Double> func2 = (a1, a2) -> Double.valueOf(a1 + a2);
        Double sum = func2.apply(10, 100);

        assertThat(sum).isEqualTo(110);
    }

    @Test
    void andThenTest() {
        BiFunction<Integer, Integer, Double> func1 = (a1, a2) -> Math.pow(a1, a2);
        Function<Double, String> func2 = (a1) -> "Result: " + a1;

        String result = func1.andThen(func2).apply(2, 3);

        assertThat(result).isEqualTo("Result: 8.0");
    }

    @Test
    void example() {
        String result = powAndReturnString(2, 3, (num) -> "result: " + num);

        assertThat(result).isEqualTo("result: 8.0");

        result = powAndReturnString(2, 3, (num) -> "PowPow: " + num);
        assertThat(result).isEqualTo("PowPow: 8.0");
    }

    public static String powAndReturnString(Integer num1, Integer num2, Function<Double, String> funcConvert){
        BiFunction<Integer, Integer, Double> funcPow = (a1, a2) -> Math.pow(a1, a2);
        return funcPow.andThen(funcConvert).apply(num1, num2);
    }
}
