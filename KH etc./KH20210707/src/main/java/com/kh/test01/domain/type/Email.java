package com.kh.test01.domain.type;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Email {

    private final String value;

    public static Email from(String email){
        return new Email(email);
    }

    @Override
    public String toString(){
        if(Objects.isNull(value)){
            return "";
        }
        return value;
    }
}
