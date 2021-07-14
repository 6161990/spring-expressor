package com.company.shop;

import com.company.shop.discount.DiscountPolicy;
import com.company.shop.discount.FixDiscountPolicy;
import com.company.shop.discount.RateDiscountPolicy;
import com.company.shop.member.MemberRepository;
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
        return new MemberServiceImpl(memberRepository());
        // AppConfig 객체는 MemoryMemberRepository 객체를 생성하고 그 참조값을 MemberServiceImpl을 생성하면서 생성자로 전달한다.
        // 클라이언트인 MemberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입시켜주는 것 같다고해서
        // => 의존성 주입 Dependency Injection
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    //but 이것도 자세히 보면 중복이 있고 역할에 따른 구현이 잘 보이지 않음
    // MemoryMemberRepository의 중복, 구현체 분리 / FixDiscountPolicy 의 구현체 분리

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy(){
    //    return new FixDiscountPolicy(); 할인 정책 변경
        return new RateDiscountPolicy();
    }

    //역할과 구현이 다 드러나는 메소드들, 애플리케이션 전체 구성이 어떻게 되는지 한눈에 파악할 수 있음





}
