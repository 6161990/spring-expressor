package com.yoon.springjdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value(staticConstructor = "of")
@AllArgsConstructor
public class RRLookUpId {
    Long id;

    public static RRLookUpId of(Long i) {
        return new RRLookUpId(i);
    }
}
