package com.specification.membership;

import java.util.Optional;

public class ServiceXXXMaker {

    public Boolean isXXXableService(ServiceXXXFactor factor) {
        Optional<Policy<ServiceXXXFactor, Boolean>>  policy = ServiceXXXPolicyFinder.getBestPolicy(factor);

        return policy.get().resultBy(factor);

    }
}
