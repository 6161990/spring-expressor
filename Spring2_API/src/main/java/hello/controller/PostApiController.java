package hello.controller;


import hello.dto.PostRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("post-api")
public class PostApiController {

    @PostMapping("/post")
    public void post(@RequestBody Map<String, Object> requestData) { //post는 RequestBody ; 'body에 request를 심었다'
        requestData.entrySet().forEach(stringObjectEntry -> {
            System.out.println("key : " + stringObjectEntry.getKey());
            System.out.println("value : " + stringObjectEntry.getValue());
        });
        //간결하게 코드를 만들자면 ,
//        requestData.forEach((key, value) -> {
//            System.out.println("key : " + key);
//            System.out.println("value : " + value);
//        });

        //근데 이 방식으로는 어떤 값을 보낼지 미리 알아야하기 때문에 data가 많아지면 힘듦.
        // DTO 이용
    }

    @PostMapping("/post2")
    public void post2(@RequestBody PostRequestDto requestDto) {
        System.out.println(requestDto);
    }
}
