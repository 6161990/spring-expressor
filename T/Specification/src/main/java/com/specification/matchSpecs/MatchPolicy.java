package com.specification.matchSpecs;

public class MatchPolicy {

    private final MatchCondition condition;
    private final Rule rule;

    public MatchPolicy(MatchCondition condition, Rule rule) {
        this.condition = condition;
        this.rule = rule;
    }
}
