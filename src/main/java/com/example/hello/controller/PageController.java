package com.example.hello.controller;


import com.example.hello.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


// 단순한 api를 만드는 서버를 작성할 수도 있지만
// 실제로 page를 return 하는 서버를 작성할 수도 있음
@Controller //html 리소스를 찾게됨
public class PageController {

    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }

    //ResponseEntity
    @ResponseBody
    @GetMapping("/user")
    public User user(){
        var user = new User();  //User user = new User();
        user.setName("rose");
        user.setAddress("청담동");
        return user;
    }
    // 이 상태에서 json을 어떻게 내려줄 것인가
    // @Controller라는 annotation은 String을 return하면
    // 리소스 (main.html)를 찾게 되지만,
    //  @ResponseBody 라는 annotation으로 객체 자체를 return 했을 때,
    // 리소스를 찾지 않고 말 그대로 @ResponseBody를 만들어서 내리겠다라는 명령임
    // but, 방법이긴 하나 쓰지 않는 것이 좋다 .
    // RestApi 로 ResponseEntity를 통해서 객체를 리턴해 json을 내려줄 수 있기 때문에
    // pageController로는 많이 쓰지 않음.
}
