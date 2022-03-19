package com.yoon.t.specification;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.yoon.t.specification.MatchCondition.expectedContainedKey;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 요구사항 1. Map 안에 "foo" 가 들어있는지 확인하라.
 * 요구사항 2. Map 안에 "bar" 조건을 추가하라.
 * 요구사항 3. Map 안에 "foo"가 아니면 false 하게 변경하라.
 * 요구사항 4. MatchCondition에 static 메소드를 추가하라.
 */
class MatchConditionTest {

    @DisplayName("object가 Map이면서 안에 foo 가 담겨있는지 확인하는 테스트")
    @Test
    void conditionIsFoo() {
        Object object = Maps.newHashMap("foo", "anyValue");

        assertThat(expectedContainedKey("Bar").isSatisfy(object)).isFalse();
        assertThat(expectedContainedKey("foo").isSatisfy(object)).isTrue();
    }

}