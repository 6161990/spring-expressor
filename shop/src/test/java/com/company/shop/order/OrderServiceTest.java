package com.company.shop.order;

import com.company.shop.AppConfig;
import com.company.shop.member.Grade;
import com.company.shop.member.Member;
import com.company.shop.member.MemberService;
import com.company.shop.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    // 의존성 주입 전
   // MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());
   // OrderService orderService = new OrderServiceImpl(discountPolicy, memberRepository);


    // 후
    MemberService memberService;
    OrderService orderService;

    @BeforeEach //test 실행 전 무조건 실행되는 메소드 에노테이션
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }


    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "TAGU", Grade.VIP );
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"Iphone", 1300000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
