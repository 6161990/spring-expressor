package com.company.shop.order;

import com.company.shop.member.Grade;
import com.company.shop.member.Member;
import com.company.shop.member.MemberService;
import com.company.shop.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "TAGU", Grade.VIP );
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"Iphone", 1300000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
