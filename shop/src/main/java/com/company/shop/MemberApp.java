package com.company.shop;

import com.company.shop.member.Grade;
import com.company.shop.member.Member;
import com.company.shop.member.MemberService;
import com.company.shop.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

        //MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository()); 의존성 주입 전
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService(); // 후

        //스프링으로 application을 구동 ApplicationContext = 스프링 컨테이너. 빈(어플리케이션)을 관리
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // name의 memberService는 AppConfig의 메소드 이름

        Member member = new Member(1L,"tagu", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = "+ member.getName());
        System.out.println("find memer = "+ findMember.getName());

    }
}
