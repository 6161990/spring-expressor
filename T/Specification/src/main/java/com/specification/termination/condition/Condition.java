package com.specification.termination.condition;


import com.specification.termination.ServiceXXXFactor;

public interface Condition {
    Boolean isSatisfy(ServiceXXXFactor factor);
}
