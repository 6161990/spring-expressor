package com.specification.termination.condition;

import com.specification.termination.ServiceXXXFactor;

public class AlwaysXXXXCondition implements Condition {

    @Override
    public Boolean isSatisfy(ServiceXXXFactor factor) {
        return factor.isAlwaysXXX();
    }
}