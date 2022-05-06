package com.specification.membership;


import com.specification.membership.condition.Condition;

import java.util.function.Function;


public class ServiceXXXPolicy implements Policy<ServiceXXXFactor, Boolean> {

    private final Condition<ServiceXXXFactor> condition;
    private final Function<ServiceXXXFactor, Boolean> what = (f) -> true;

    public ServiceXXXPolicy(Condition<ServiceXXXFactor> condition) {
        this.condition = condition;
    }

    @Override
    public Function<ServiceXXXFactor, Boolean> outer(){
        return what;
    }

    @Override
    public boolean isSatisfy(ServiceXXXFactor factor) {
        return condition.isSatisfy(factor);
    }
}
