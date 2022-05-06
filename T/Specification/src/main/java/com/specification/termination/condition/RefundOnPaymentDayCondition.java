package com.specification.termination.condition;


import com.specification.termination.ServiceXXXFactor;

import static java.time.LocalDate.now;

public class RefundOnPaymentDayCondition implements Condition {

    @Override
    public Boolean isSatisfy(ServiceXXXFactor factor) {
        return factor.getJoinedAt().toLocalDate().isEqual(now());
    }
}
