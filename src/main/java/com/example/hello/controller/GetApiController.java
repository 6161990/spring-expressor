package com.example.hello.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path = "/hello")
    // (근래) 방법 1 : @GetMapping
    // 원래  @GetMapping의 DEFALUT 값이 PATH이므로 path=는 안적어줘도됨, 여기서는 명확하게 경로가 어떤 것이다 지정해봄.
    public String getHello(){
        return "get Hello";
    }

    @RequestMapping(path = "/hi", method = RequestMethod.GET)
    // (예전) 방법 2 : @RequestMapping , 이렇게 해줘도 되긴 하지만, 이럴경우
    // get , post, put, delete 모두 동작하게 됨. 그래서 method를 get으로 명시해줘야함
    public  String hi(){
        return "hi";
    }

    // http://localhost:9090/api/get 까지는 고정 , 상단 클래스가 RequestMapping으로 get까지는 가지고 있기 때문.
    // http://localhost:9090/api/get/ 이 뒤로 변화하는 값을 넣음
    // http://localhost:9090/api/get/path-variable/{kimyoonji}  ; 아이디라던지, 이름이라던지 등등 의 variable한 값을 넣음
    // but, 변화를 할 때 마다 매번 주소를 바꿔 줄 수는 없음. 때문에 path-variable/{변화할 값의 변수(name)}으로 넣어주면됨
    @GetMapping("/path-variable/{name}") //path-variable ; 변하는 값(주소에 대문자 쓰지 않으므로 대쉬로 가독성 높인다)
    public String pathVariable(@PathVariable String name){ //변할 수 있는 값으로 GetMapping을 통해 String name 을 받겠다.
        //단!!! GetMapping통해 {}안에 넣은 변수 이름이 @PathVariable에 넣은 변수 이름과 동일해야한다.
        System.out.println("PathVariable : "+name);
        return name;
    }
}
