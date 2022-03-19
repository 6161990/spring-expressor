package com.yoon.t.specification;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ScheduleTriggerRuleMatchConditionTest {

    @Test
    void name() {
        Object object = Maps.newHashMap("foo", "anyValue");

        MatchCondition sut = new MatchCondition();

        assertThat(sut.isSatisfy(object)).isTrue();
    }

}