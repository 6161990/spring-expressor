package com.example.client.service;

import com.example.client.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    //http://localhost:9090/api/server/hello
    //response

    public UserResponse hello(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name","lisa")
                .queryParam("age",23)
                .encode()
                .build()
                .toUri();
        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
//      return result;

//        ResponseEntity<String> result2 = restTemplate.getForEntity(uri, String.class);
//        System.out.println(result2.getStatusCode());
//        System.out.println(result2.getBody());
//        return result2.getBody();

        //getForEntity 같은 경우 ResponseEntity<T>으로 받을 수 있는데
        //getForObject 같은 경우 지정한 <T>으로 결과를 받을 수 있음
        //가져 오겠다는 의미의 get이 아님 , http의 get임

        ResponseEntity<UserResponse> result3 = restTemplate.getForEntity(uri, UserResponse.class);
        System.out.println(result3.getStatusCode());
        System.out.println(result3.getBody());
        return result3.getBody();


    }
}
