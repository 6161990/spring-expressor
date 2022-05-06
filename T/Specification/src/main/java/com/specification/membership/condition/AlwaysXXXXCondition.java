package com.specification.membership.condition;

import com.specification.membership.ServiceXXXFactor;

public class AlwaysXXXXCondition implements Condition<ServiceXXXFactor> {

    @Override
    public Boolean isSatisfy(ServiceXXXFactor factor) {
        return factor.isExistAlwaysXXX();
    }
}