package com.company.shop;

import com.company.shop.member.Grade;
import com.company.shop.member.Member;
import com.company.shop.member.MemberService;
import com.company.shop.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        //MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository()); 의존성 주입 전
        MemberService memberService = appConfig.memberService(); // 후
        Member member = new Member(1L,"tagu", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = "+ member.getName());
        System.out.println("find memer = "+ findMember.getName());

    }
}
