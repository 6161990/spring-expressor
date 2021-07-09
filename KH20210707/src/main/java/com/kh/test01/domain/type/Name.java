package com.kh.test01.domain.type;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Name {

    private final String value; //이 클래스 안에서만 사용 가능

    public static Name from(String name){
        //팩토리 메소드 구현 형태 new를 그대로 사용하는 것보다 안전하게 사용
        //인스턴스 생성시 안전하도록
        return new Name(name);
    }

    public boolean equals(String name){
        return value.equals(name);
    }

    @Override
    public String toString(){
        if(Objects.isNull(value)){
            return "";
        }
        return value;
    }
}
