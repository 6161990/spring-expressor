package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }

    /**
     *  @Controller같은 경우는 return을 String 으로 하면 뷰리졸버로 찾게됨.
     *  @RestController로 지정하면 return String 값을 그냥 body에 뿌려줌
     *  @RestController를 클래스에 지정해줘도되지만 메소드에 @ResponseBody를 달아줘도 됨.
     *  그럼 return 값이 HTTP Message로 body에 찍힘
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge){

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }


    /**
     * requestParamV2 애서 @RequestParam("username") ,@RequestParam("age") 괄호 생략
     * HTTP 파라미터 이름이 변수 이름과 같다면 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age){

        log.info("username={}, age={}", username, age);
        return "ok";
    }


    /**
     * requestParamV3 에서 더더 축약 버전 @RequestParam 마저 생략
     * HTTP 파라미터 이름이 변수 이름과 같고,
     * String, int, Integer 등의 단순 타입이면 @ RequestParam도 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * required = false 면 null 값이 들어올 수 없게 막아놓는 것.
     * but, int 형(age)은 null값이 올 수 없으므로
     * 객체형 Integer로 받아주어야 함.
     * but!!!! 주의 , /request-param-required?username=
     * 처럼 파라미터 이름만 있고 값이 없는 경우 = "" -> 빈문자로 ok통과됨
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * defaultValue를 넣어주면 파라미터에 값이 없는 경우 기본값이 들어가버림(빈문자도가능)
     * required는 사실상 의미가 없어짐. 기본값이라도 들어갈 것이기 때문에.
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    /**
     * modelAttributeV_1() 와 modelAttributeV1의 차이 = @ModelAttribute
     * 스프링 MVC는 @Model Attibute가 있으면 다음을 실행
     * 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다.
     * 그리고 해당 프로퍼티의 setter를 호출해서 파라미터의 값을 입력(바인딩) 한다.
     */
    @ResponseBody
    @RequestMapping("/")
    public String modelAttributeV_1(@RequestParam String username, @RequestParam int age) {
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        helloData.getAge();
        return "ok";
    }

    /**
     * 근데 !!!!!!!!!! @ModelAttribute도 생략할 수 있음
     * @RequestParam도 생략할 수 있으니 혼란이 발생할 수 있음
     * 다음과 같은 규칙 적용
     * String, int , Integer같은 단순 타입 = @RequestParam
     * 나머지 @ModelAttribute (argument resolver로 지정해둔 타입 외)
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
