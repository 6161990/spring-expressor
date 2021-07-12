package com.company.shop.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        //given
        Member member = new Member(1L, "tagu", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);

        //하지만 이 설계에서 문제점이있음
        //1. 다른 저장소로 변경할 때 OCP원칙이 준수가 잘 안됨.
        //2. DIP 가 잘 지켜지지않음
        //3. 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있음
        // -> 주문까지 만들고나서 문제점과 해결방안을 설명명
    }

}