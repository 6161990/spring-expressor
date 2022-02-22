package com.company.shop.beanFind;

import com.company.shop.AppConfig;
import com.company.shop.member.MemberService;
import com.company.shop.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = "+ memberService);
        System.out.println("memberService.getClass() = "+memberService.getClass());
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){ //하지만 구체에 의존하는 TEST이기 때문에 좋은 TEST라고는 볼 수 없음, 유연성이 떨어짐
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        System.out.println("memberService = "+ memberService);
        System.out.println("memberService.getClass() = "+memberService.getClass());
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
    //    MemberService memberService = ac.getBean("xxxxx", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class, ()->ac.getBean("xxxxx", MemberService.class));
        //오른쪽 항에서 터진 예외가 왼쪽항이라면 test 성공
    }
}
