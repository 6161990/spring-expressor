package com.company.design.specification;

class ScheduleTriggerRuleMatchConditionTest {

    void name() {
        Object object = new Object();
        // foo contains

        ScheduleTriggerRuleMatchCondition sut = new ScheduleTriggerRuleMatchCondition();

        assertThat(sut.isSatisfy(object)).isTrue();
    }

}