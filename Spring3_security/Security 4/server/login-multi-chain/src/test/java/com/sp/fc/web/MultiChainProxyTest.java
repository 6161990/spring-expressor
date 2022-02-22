package com.sp.fc.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sp.fc.web.student.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MultiChainProxyTest {

    @LocalServerPort
    int port;

    RestTemplate client = new RestTemplate();
    TestRestTemplate testClient = new TestRestTemplate("choi", "1");

    private String greetingUrl(){
        return "http://localhost:"+port+"/greeting";
    }


    @DisplayName("1. 학생 조사")
    @Test
    void test_1() throws JsonProcessingException {
        String url = format("http://localhost:%d/api/teacher/students", port);  //request를 보내는 url가 'api' 아래로 !

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Basic "+ Base64.getEncoder().encodeToString(
                "choi:1".getBytes() //BasicAuthenticationToken에 choi:1 로 데이터 전송
        ));
        HttpEntity entity = new HttpEntity(null, headers);
        ResponseEntity<String> response = client.exchange(greetingUrl(), HttpMethod.GET, entity, String.class);
       // BasicTokenHeader에 response list를 보내기 위해 아래와 같이
        List<Student> list = new ObjectMapper().readValue(response.getBody(),
                new TypeReference<List<Student>>(){});
        System.out.println(list);
        assertEquals(3, list.size());
    }

    @DisplayName("2. choi:1로 로그인해서 학생 리스트를 내려받는다.")
    @Test
    void test_2(){
        ResponseEntity<List<Student>> resp = testClient.exchange("http://localhost:" +port+ "/api/teacher/students",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>(){
                });
        Assertions.assertNotNull(resp.getBody());
        Assertions.assertEquals(3, resp.getBody().size());
    }






}
