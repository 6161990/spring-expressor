package com.yoon.t.specification.utils.specs;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.yoon.t.specification.utils.specs.MapCondition.expected;
import static org.assertj.core.api.Assertions.assertThat;

class AndConditionTest {

    @Test
    void personAgeAndNameCondition() {
        Map<String, Object> factor = Maps.newHashMap("name", "foo");

        Condition condition = new AndCondition(new AndCondition(expected("name", "foo"),
                expected("age", "18")), expected("height", "158"));

        assertThat(condition.isSatisfy(factor)).isFalse();

        factor.put("age", "18");
        factor.put("height", "158");
        assertThat(condition.isSatisfy(factor)).isTrue();

    }

    @Test
    void andTest() {
        Condition condition =
                expected("age", "200")
                        .and(expected("name", "Foo"))
                        .and(expected("height", "108"));

    }
}