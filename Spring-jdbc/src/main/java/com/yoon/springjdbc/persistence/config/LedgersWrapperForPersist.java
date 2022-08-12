package com.yoon.springjdbc.persistence.config;

import com.yoon.springjdbc.domain.Ledger;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor(access = AccessLevel.MODULE)
public class LedgersWrapperForPersist {

    private final List<Ledger> ledgers;

    public static LedgersWrapperForPersist of(List<Ledger> ledgers) {
        return new LedgersWrapperForPersist(ledgers);
    }
}
