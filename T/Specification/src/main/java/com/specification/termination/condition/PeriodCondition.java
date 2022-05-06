package com.specification.termination.condition;


import com.specification.termination.ServiceXXXFactor;

import static java.time.LocalDate.now;

public class PeriodCondition implements Condition {

    @Override
    public Boolean isSatisfy(ServiceXXXFactor factor) {
        return factor.getImpossibleXXXAt().toLocalDate().isAfter(now()) && factor.getMembershipPeriod().includeMembershipPeriod(now());
    }
}
