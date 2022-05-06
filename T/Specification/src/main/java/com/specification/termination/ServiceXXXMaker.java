package com.specification.termination;

import java.util.Optional;

public class ServiceXXXMaker {

    public Boolean isXXXableService(ServiceXXXFactor factor) {
        Optional<ServiceXXXPolicy> policy = ServiceXXXPolicyFinder.getBestPolicy(factor);

        return policy.map(ServiceXXXPolicy::isTerminable).orElse(false);

    }
}
