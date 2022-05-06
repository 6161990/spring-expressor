package com.specification.termination;


import com.specification.termination.condition.Condition;

public class ServiceXXXPolicy {

    private final ServiceXXXFactor factor;
    private Condition condition;

    public ServiceXXXPolicy(ServiceXXXFactor factor, Condition condition) {
        this.factor = factor;
        this.condition = condition;
    }

    public boolean isTerminable() {
        return isSatisfy();
    }

    public Boolean isSatisfy() {
        return condition.isSatisfy(factor);
    }
}
