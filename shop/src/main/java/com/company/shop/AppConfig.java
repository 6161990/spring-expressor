package com.company.shop;

import com.company.shop.discount.FixDiscountPolicy;
import com.company.shop.member.MemberService;
import com.company.shop.member.MemberServiceImpl;
import com.company.shop.member.MemoryMemberRepository;
import com.company.shop.order.OrderService;
import com.company.shop.order.OrderServiceImpl;

public class AppConfig {
    // AppConfig ; 애플리케이션의 실제동작에 필요한 구현 객체를 생성해주는 클래스
    // 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입해준다

    // MemberServiceImpl 과 OrderServiceImpl의 입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없음
    // MemberServiceImpl 과 OrderServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부 AppConfig에서 결정됨
    // MemberServiceImpl 과 OrderServiceImpl은 이제부터 '의존관계에 대한 고민은 외부'에 맡기고 실행에만 집중 할 수 있음

    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
        // AppConfig 객체는 MemoryMemberRepository 객체를 생성하고 그 참조값을 MemberServiceImpl을 생성하면서 생성자로 전달한다.
        // 클라이언트인 MemberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입시켜주는 것 같다고해서
        // => 의존성 주입 Dependency Injection
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
