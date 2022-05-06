package com.specification.membership;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MembershipPeriod {
    LocalDateTime begin;
    LocalDateTime end;

    public MembershipPeriod(LocalDateTime begin, LocalDateTime end) {
        this.begin = begin;
        this.end = end;
    }

    public boolean includeMembershipPeriod(LocalDate now) {
        return begin.toLocalDate().isBefore(now) && end.toLocalDate().isAfter(now);
    }
}
