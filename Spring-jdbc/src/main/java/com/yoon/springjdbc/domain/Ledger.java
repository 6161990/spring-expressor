package com.yoon.springjdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "of")
@AllArgsConstructor
public class Ledger {
    long tax;
    long valueOfSupply;
    LocalDateTime occurredAt;

    public long getAmount() {
        return this.tax + this.valueOfSupply;
    }

    public String getValue() {return this.toString(); }
}


