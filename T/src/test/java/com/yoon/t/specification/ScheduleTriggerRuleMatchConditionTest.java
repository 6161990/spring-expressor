package com.yoon.t.specification;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ScheduleTriggerRuleMatchConditionTest {

    @DisplayName("object가 Map이면서 안에 foo 가 담겨있는지 확인하는 테스트")
    @Test
    void name() {
        Object object = Maps.newHashMap("foo", "anyValue");

        MatchCondition sut = new MatchCondition();

        assertThat(sut.isSatisfy(object)).isTrue();
    }

}