package com.kh.test01.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// 별 다른 인스턴스를 생성하지 않고 사용할 수 있는 법 annotation
// DI 컨테이너는 @Controller, @Repository , @Service 등 여러 annotation으로 표시된 클래스의
// 인스턴스를 미리 생성하고 저장한다는 의미
// 그리고 인스턴스가 필요한 곳에 알맞게 분배하여 일일히 new하지 않고도 인스턴스를 사용할 수 있게됨
@Controller  // Spring Framework 의 DI 컨테이너에 이 클래스를 등록한다는 의미.
@RequiredArgsConstructor
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello(Model model){
        return "hello";
    }


}
