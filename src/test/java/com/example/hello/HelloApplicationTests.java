package com.example.hello;

import com.example.hello.objectMapper.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("-------");

        //object mapper?
        //Text 형태의 JSON을 Object로 변경시켜주고
        //Object 형태를 Text 형태의 JSON으로 , 반대로도 변경시켜준다.

        //이는
        //Controller 에서 request로 JSON(text)형태가 들어오면 자동적으로 object로 바꿔주었고
        //response를 object로 return하게 되면 자동으로 JSON(text)로 바꿨던 것.

        var objectMapper = new ObjectMapper();
        // ((주의사항))
        // 1. objectMapper는 get메소드를 참조 하므로, 객체(User)에 get메소드를 추가해줘야한다.
        //   단!!!! 내가 작성한 클래스가 objectMapper에서 활용된다고 할 때는 다른 기타 메소드를 get으로 이름지으면 안된다.
        // 2. objectMapper는 defalut 생성자를 필요로 한다.


        //object -> text
        var user = new User("lisa", 24, "010-2243-3982");
        var text = objectMapper.writeValueAsString(user);
        System.out.println(text);

        //text -> object
        //첫번째는 JSON 텍스트, 두번째로는 바꿀 타입의 클래스
        var objectUser = objectMapper.readValue(text, User.class);
        System.out.println(objectUser);
    }

}
