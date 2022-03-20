package com.yoon.t.specification.utils.specs;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AndConditionTest {

    @Test
    void personAgeAndNameCondition() {
        Map<String, Object> factor = Maps.newHashMap("name", "foo");

        Condition condition = new AndCondition(new AndCondition(MapCondition.expected("name", "foo"),
                MapCondition.expected("age", "18")), MapCondition.expected("height", "158"));

        assertThat(condition.isSatisfy(factor)).isFalse();

        factor.put("age", "18");
        factor.put("height", "158");
        assertThat(condition.isSatisfy(factor)).isTrue();

    }

}