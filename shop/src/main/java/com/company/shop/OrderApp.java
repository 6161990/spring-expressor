package com.company.shop;

import com.company.shop.member.Grade;
import com.company.shop.member.Member;
import com.company.shop.member.MemberService;
import com.company.shop.member.MemberServiceImpl;
import com.company.shop.order.Order;
import com.company.shop.order.OrderService;
import com.company.shop.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "minJ", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"IPhone", 1300000);
        System.out.println("order = "+ order);
        System.out.println("discount Price ="+ order.calculatePrice());
    }
}
