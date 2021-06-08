package com.example.hello.controller;

import com.example.hello.dto.UesrRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    //but, /path-variable/{name}과 @PathVariable String name 의 이름이 다를 때 !!!(이름을 일치 시켜줄 수 없을 때)
    @GetMapping("/path-variable2/{name}")
    public String pathVariable2(@PathVariable(name = "name") String pathName){
        System.out.println("PathVariable : "+pathName);
        return pathName;
    }

    // what is queryParameter ?
    // search?q=%EC%9D%B8%ED%85%94%EB%A6%AC%EC%A0%9C%EC%9D%B4
    // &oq=%EC%9D%B8%ED%85%94%EB%A6%AC%EC%A0%9C%EC%9D%B4
    // &aqs=chrome..69i57j69i59l6j69i61.1173j0j15
    // &sourceid=chrome
    // &ie=UTF-8
    // => ?key=value
    //    &key2=value2 : 물음표로 시작해서 key = value 이런식의 구성으로 되어있따.

    //http://localhost:9090/api/get/query-param?user=yoonji&email=jineon@gamil.com&age=25
    @GetMapping(path = "query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){
        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach( entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+"="+entry.getValue()+"\n");
        });
        return sb.toString();
    }

    //queryParam을 Map으로 받는 경우에는 key를 알 수가 없음
    //key를 user, email, age로 명확하게 지정을 한 경우에는 어떻게 해야할까?
    @GetMapping("query-param2")
    public String queryParam2(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age
    ){
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name+" "+email+" "+age;
    }
    // 하지만, 모든 파라미터마다  @RequestParam 하므로 이 방식은 비효율적.
    // so, DTO를 이용하는 것이 바람직.


    @GetMapping("query-param3")
    public String queryParam3(UesrRequest userRequest){
        //@RequestParam 을 붙여주지않고, 객체를 전달해주면 물음표 뒤에 있는 값들은 스프링 부트에서 판단해준다.
        //?user=yoonji&email=jineon@gamil.com&age=25   에서
        //key(user, email, age)에 해당하는 것들을 해당객체(DTO)에서 찾아 변수 매칭을 해준다.
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }

}
