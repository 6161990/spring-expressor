package hello.controller;

import hello.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //해당 class는 REST API 처리하는 Controller
@RequestMapping("/api")//주소할당: RequestMapping URI를 지정해주는 Annotation
public class ApiController {

    @GetMapping("/hello") //get방식으로 ; http://localhost:9090/api/hello
    public String hello() {
        return "hello spring boooooot!";
    }


    // TEXT
    @GetMapping("/text")
    public String text(@RequestParam String account) {
        return account;
    }

    //JSON
    //request -> object mapper -> object -> method -> object -> object mapper -> json -> response
    @PostMapping("/json")
    public User json(@RequestBody User user) {
        return user;
    }
    //보통은  200 ok

    //Put은 200, 201 둘 다 쓴. 리소스가 '수정'되었을 때는 200 , '생성'되었을 때는 201이 내려옴.
    //그럼 response 201을 어떻게 내려줄까?
    //response 를 내려줄 때 http status를 내려줌
    // How? ResponseEntity 라는 객체를 통해서! <제네릭> 타입으로 내려주기
    @PostMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user) {
        // ResponseEntity.ok(user);  로 body를 넣어줄 수도 있고
        // status 도 201, 200 등으로 지정할 수 있음
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
        //response 에 대한 커스텀마이징이 필요하다면 이렇게 직접 ResponseEntity를 사용하면된다.
    }
}