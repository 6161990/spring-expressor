package com.specification.membership;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ServiceXXXFactor {

    private final LocalDateTime joinedAt;
    private final LocalDateTime impossibleXXXAt;
    private final MembershipPeriod membershipPeriod;
    private final boolean isExistAlwaysXXX;

    public ServiceXXXFactor(LocalDateTime joinedAt, LocalDateTime impossibleXXXAt, MembershipPeriod membershipPeriod, boolean isExistAlwaysXXX) {
        this.joinedAt = joinedAt;
        this.impossibleXXXAt = impossibleXXXAt;
        this.membershipPeriod = membershipPeriod;
        this.isExistAlwaysXXX = isExistAlwaysXXX;
    }
}
