package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

//    @GetMapping("/hello")
//    public String hello(){
//        return "hello server";
//    }
    @GetMapping("/naver")
    //https://openapi.naver.com/v1/search/local.json?
    // query=%EC%A3%BC%EC%8B%9D
    // &display=10
    // &start=1
    // &sort=random
    public String naver(){

        String query ="갈비집";
//        String encode = Base64.getEncoder().encodeToString(query.getBytes(StandardCharsets.UTF_8));
        //인코딩 밑에서 해주므로 "갈비집"을 encoding 할 필요 없음

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query","갈비집")
                .queryParam("display",10)
                .queryParam("start",1)
                .queryParam("sort","random")
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> req = RequestEntity //header를 세팅하고 get으로 요청하기 때문에 Void
                .get(uri)
                .header("X-Naver-Client-Id","VfdK_MbHd4ZcGj9XNwOS")
                .header("X-Naver-Client-Secret","KFYtie4hap")
                .build();
        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
        return result.getBody();
    }

    @GetMapping("/hello")
    public User hello(@RequestParam String name, @RequestParam int age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public Req<User> post(@RequestBody Req<User> user,
                     @PathVariable int userId,
                     @PathVariable String userName,
                     @RequestHeader("x-authorization") String authorization,
                     @RequestHeader("custom-header") String customHeader
    ){
        log.info("userId : {}, userName : {}", userId, userName);
        log.info("x-authorization : {}, custom-header : {}", authorization, customHeader);
        log.info("client req : {}",user);

        Req<User> response = new Req<>();
        response.setHeader(
                new Req.Header()
        );
        response.setResBody(
                user.getResBody()
        );
        return response;
    }
}
