package com.specification.termination;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ServiceXXXFactor {

    private final LocalDateTime joinedAt;
    private final LocalDateTime impossibleXXXAt;
    private final MembershipPeriod membershipPeriod;
    private final boolean isAlwaysXXX;

    public ServiceXXXFactor(LocalDateTime joinedAt, LocalDateTime impossibleXXXAt, MembershipPeriod membershipPeriod, boolean isAlwaysXXX) {
        this.joinedAt = joinedAt;
        this.impossibleXXXAt = impossibleXXXAt;
        this.membershipPeriod = membershipPeriod;
        this.isAlwaysXXX = isAlwaysXXX;
    }
}
