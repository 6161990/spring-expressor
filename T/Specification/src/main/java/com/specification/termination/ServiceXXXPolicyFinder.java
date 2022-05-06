package com.specification.termination;

import com.specification.termination.condition.AlwaysXXXXCondition;
import com.specification.termination.condition.PeriodCondition;
import com.specification.termination.condition.RefundOnPaymentDayCondition;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ServiceXXXPolicyFinder {

    public static Optional<ServiceXXXPolicy> getBestPolicy(ServiceXXXFactor factor) {
        List<ServiceXXXPolicy> policies = getServiceXXXPolicies(factor);

        for (ServiceXXXPolicy policy : policies) {
            if (policy.isSatisfy()) {
                return Optional.of(policy);
            }
        }

        return Optional.empty();
    }

    private static List<ServiceXXXPolicy> getServiceXXXPolicies(ServiceXXXFactor factor) {
        return Arrays.asList(
                new ServiceXXXPolicy(factor, new RefundOnPaymentDayCondition()),
                new ServiceXXXPolicy(factor, new PeriodCondition()),
                new ServiceXXXPolicy(factor, new AlwaysXXXXCondition())
        );
    }
}
