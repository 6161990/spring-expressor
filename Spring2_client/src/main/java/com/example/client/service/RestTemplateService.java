package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
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

    public UserResponse post(){
        // http://localhost:9090/api/server/user/{userId}/name/{userName}

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"jenny")
                .toUri();
        System.out.println(uri);

        // http body -> object -> object mapper -> json -> rest template -> htt p body json
        // 내가 보내고 싶은 request data를 json으로 만드는 게 아니라 object로 만들면 mapper가 자연적으로 json으로 바꿔주고
        // rest template에서 body에다 json으로 넣어서 쏨
        UserRequest req = new UserRequest();
        req.setName("jenny");
        req.setAge(24);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri, req, UserResponse.class);
        //해당 주소(uri)에 request body를 만들어서 UserResponse객체로 응답을 받을거야
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();
    }

    public UserResponse exchange(){
        // http://localhost:9090/api/server/user/{userId}/name/{userName}

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"jenny")
                .toUri();
        System.out.println(uri);

        UserRequest req = new UserRequest();
        req.setName("jenny");
        req.setAge(24);

        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "fffff")
                .body(req);


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity, UserResponse.class);
        return response.getBody();
    }


    public Req<UserResponse> genericExchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"jenny")
                .toUri();
        System.out.println(uri);

        UserRequest userRequest = new UserRequest();
        userRequest.setName("jenny");
        userRequest.setAge(24);

        Req<UserRequest> req = new Req<UserRequest>();
        req.setHeader(
                new Req.Header()
        );
        req.setResBody(
                userRequest
        );
        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "fffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Req<UserResponse>> response
                = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<Req<UserResponse>>(){});
        return response.getBody();
    }
}
