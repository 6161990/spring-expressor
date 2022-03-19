package com.yoon.t.specification;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 요구사항 1. Map 안에 "foo" 가 들어있는지 확인하라.
 * 요구사항 2. Map 안에 "bar" 조건을 추가하라.
 */
class ScheduleTriggerRuleMatchConditionTest {

    @DisplayName("object가 Map이면서 안에 foo 가 담겨있는지 확인하는 테스트")
    @Test
    void conditionIsFoo() {
        Object object = Maps.newHashMap("foo", "anyValue");

        MatchCondition sut = new MatchCondition();

        assertThat(sut.isSatisfy(object)).isTrue();
    }

    @DisplayName("object가 Map이면서 안에 bar 가 담겨있는지 확인하는 테스트")
    @Test
    void conditionIsBar() {
        Object object = Maps.newHashMap("bar", "anyValue");

        MatchCondition sut = new MatchCondition();

        assertThat(sut.isSatisfy(object)).isTrue();
    }

}