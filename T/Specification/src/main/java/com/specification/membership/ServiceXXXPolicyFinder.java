package com.specification.membership;

import com.specification.membership.condition.AlwaysXXXXCondition;
import com.specification.membership.condition.PeriodCondition;
import com.specification.membership.condition.RefundOnPaymentDayCondition;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ServiceXXXPolicyFinder {

    public static Optional<Policy<ServiceXXXFactor, Boolean>> getBestPolicy(ServiceXXXFactor factor) {
        List<ServiceXXXPolicy> policies = getServiceXXXPolicies();

        for (ServiceXXXPolicy policy : policies) {
            if (policy.isSatisfy(factor)) {
                return Optional.of(policy);
            }
        }

        return Optional.empty();
    }

    private static List<ServiceXXXPolicy> getServiceXXXPolicies() {
        return Arrays.asList(
                new ServiceXXXPolicy(new RefundOnPaymentDayCondition()),
                new ServiceXXXPolicy(new PeriodCondition()),
                new ServiceXXXPolicy(new AlwaysXXXXCondition())
        );
    }
}
