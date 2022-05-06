package com.specification.membership.condition;


import com.specification.membership.ServiceXXXFactor;

import static java.time.LocalDate.now;

public class RefundOnPaymentDayCondition implements Condition<ServiceXXXFactor> {

    @Override
    public Boolean isSatisfy(ServiceXXXFactor factor) {
        return factor.getJoinedAt().toLocalDate().isEqual(now());
    }
}
