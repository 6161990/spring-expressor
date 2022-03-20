package com.yoon.t.specification;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.yoon.t.specification.MatchCondition.expected;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step 1. Map 안에 "foo" 가 들어있는지 확인하라.
 * Step 2. Map 안에 "bar" 조건을 추가하라.
 * Step 3. Map 안에 "foo"가 아니면 false 하게 변경하라.
 * Step 4. MatchCondition 에 static 메소드를 추가하라.
 * Step 5. MatchCondition 에 Map key와 value를 확인하는 메소드를 추가하라.
 * Step 6. START REFACTORING
 * Step 7. 더 많은 조건을 만들어보자.
 *         PersonAgeAndNameCondition
 *
 */
class MatchConditionTest {

    @DisplayName("object가 Map이면서 안에 foo 가 담겨있는지 확인하는 테스트")
    @Test
    void conditionIsFoo() {
        Object factor = Maps.newHashMap("foo", "ooooo");

        assertThat(expected("foo", "xxxxx").isSatisfy(factor)).isFalse();
        assertThat(expected("foo", "ooooo").isSatisfy(factor)).isTrue();
    }

    @Test
    void personAgeAndNameCondition() {

        Map<String, Object> map = Maps.newHashMap("name", "foo");

        boolean satisfy = new PersonAgeAndNameCondition().isSatisfy(map);
        assertThat(satisfy).isFalse();

    }

}