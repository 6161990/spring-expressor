package com.yoon.springjdbc.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class OrderId {
    String id;
}
