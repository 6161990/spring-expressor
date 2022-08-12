package com.yoon.springjdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
@Value(staticConstructor = "of")
public class ConfirmationValue {
    ConfirmationType type;
    Object value;

    public static ConfirmationValue of(ConfirmationType type, Object value) {
        return new ConfirmationValue(type, value);
    }
}
