package com.specification.matchSpecs.specs;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class AndConditionTest {

    @Test
    void personAgeAndNameCondition() {
        Map<String, Object> factor = Maps.newHashMap("name", "foo");

        Condition condition = new AndCondition(new AndCondition(MapCondition.expected("name", "foo"),
                MapCondition.expected("age", "18")), MapCondition.expected("height", "158"));

        Assertions.assertThat(condition.isSatisfy(factor)).isFalse();

        factor.put("age", "18");
        factor.put("height", "158");
        Assertions.assertThat(condition.isSatisfy(factor)).isTrue();

    }

    @Test
    void andTest() {
        Map<String, Object> factor = new HashMap<>();

        Condition condition = new LessThanEqualsCondition("age", 2000)
                        .and(MapCondition.expected("name", "foo"))
                        .and(new GreaterThanEqualsCondition("height", 108));

        factor.put("age", 201);
        factor.put("name", "foo");
        factor.put("height", 108);

        Assertions.assertThat(condition.isSatisfy(factor)).isTrue();

    }
}