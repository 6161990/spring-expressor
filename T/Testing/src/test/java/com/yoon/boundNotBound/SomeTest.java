package com.yoon.boundNotBound;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class SomeTest {

    @DisplayName("경계값 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1})
    void boundTest(int b){

    }

    @Test
    @DisplayName("두가지 정의역 테스트  true ,false")
    void 정의역_테스트(){
        boolean b = true;

        SomeThing sut = new SomeThing();

        boolean actual = sut.someMethod(b);

        assertThat(actual).isTrue();

        /** SomeApplication -> com.yoon.boundNotBoundTest.SomeThing -> BlockMember(isSatisfy) -> com.yoon.boundNotBoundTest.Command
         *  테스트 해야할 곳이 점점 밀려나가면서 제자리를 찾는다.
         **/
    }

    // 1. sut 에게 valid 를 물어본다.
    // 2. sut를 넘겨주는 command 객체가 언제나 valid 함을 설정한다.

    @Test
    @DisplayName("command는 언제나 올바른 놈이다. value다. 그래서 command는 테스트할 필요가 없다")
    void 테스트를_위한_객체(){
        Command command = new Command(-1, Type.A);
        SomeThing sut = new SomeThing();

        boolean actual = sut.someMethod(command);

        assertThat(actual).isTrue();
    }


}
