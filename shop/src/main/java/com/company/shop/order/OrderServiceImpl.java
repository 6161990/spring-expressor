package com.company.shop.order;

import com.company.shop.discount.DiscountPolicy;
import com.company.shop.discount.FixDiscountPolicy;
import com.company.shop.member.Member;
import com.company.shop.member.MemberRepository;
import com.company.shop.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
