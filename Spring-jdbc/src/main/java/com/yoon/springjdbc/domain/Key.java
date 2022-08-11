package com.yoon.springjdbc.domain;

import lombok.Data;
import lombok.Value;

@Value(staticConstructor = "of")
public class Key {

    String id;

}
