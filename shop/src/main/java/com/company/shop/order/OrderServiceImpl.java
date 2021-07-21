package com.company.shop.order;

import com.company.shop.annotation.MainDiscountPolicy;
import com.company.shop.discount.DiscountPolicy;
import com.company.shop.member.Member;
import com.company.shop.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
// 1   private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
// 2  private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //문제 1 discountPolicy는 추상(DiscountPolicy)과 구체(FixDiscountPolicy) 둘 다 의존하고 있다. DIP위반(구현에도 의존하는 상황)
    //문제 2 비율 할인으로 정책을 변경하면 OrderServiceImpl의 클래스를 수정해야한다. OCP위반(변경하지 않고 확장 불가)
    //해결 방안 ?
    // DIP를 위반하지 않도록 추상에만 의존하도록 ~!!
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;
    //but, 이 상태로 실행하면 NPE(Null Point Exception)이 발생한다. 그럼 어떻게 완성?
    // 누군가가 클라이언트인 OrderServiceImpl 에 DiscountPolicy의 구현 객체를 대신 생성하고 주입시켜주어야한다.
    // => AppConfig를 통해 생성자 주입 추상화(인터페이스)에만 의존하게됨


    //AppConfig 를 통해서 구현당함
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
