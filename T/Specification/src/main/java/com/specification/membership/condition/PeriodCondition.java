package com.specification.membership.condition;


import com.specification.membership.ServiceXXXFactor;

import static java.time.LocalDate.now;

public class PeriodCondition implements Condition<ServiceXXXFactor> {

    @Override
    public Boolean isSatisfy(ServiceXXXFactor factor) {
        return factor.getImpossibleXXXAt().toLocalDate().isAfter(now()) && factor.getMembershipPeriod().includeMembershipPeriod(now());
    }
}
