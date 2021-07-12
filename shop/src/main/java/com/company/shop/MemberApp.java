package com.company.shop;

import com.company.shop.member.Grade;
import com.company.shop.member.Member;
import com.company.shop.member.MemberService;
import com.company.shop.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L,"tagu", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = "+ member.getName());
        System.out.println("find memer = "+ findMember.getName());

    }
}
