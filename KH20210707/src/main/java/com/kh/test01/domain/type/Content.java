package com.kh.test01.domain.type;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Content {

    private final String value;

    public static Content from(String content){
        return new Content(content);
    }

    @Override
    public String toString(){
        return value;
    }
}
