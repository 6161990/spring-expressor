package com.example.application.data.service;

import com.example.application.data.views.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

import static com.example.application.data.service.Constants.*;
import static org.springframework.http.HttpMethod.GET;


@Service
public class UserSearchService {

    @Autowired
    public RestTemplate restTemplate;

    private String gatewayApiUrl;

    public User getUserById(String userId) {
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(gatewayApiUrl)
                .path("/")
                .path(userId)
                .queryParam(VERSION, V_0_1_0)
                .build();

        ResponseEntity<User> response =  restTemplate.exchange(uri.toUriString(), GET, getHttpEntity(), User.class);

        return response.getBody();
    }

    public List<User> getUserByName(String name) {
        UriComponents uri = getUriCommonBuilder()
                .queryParam("name", name)
                .build();

        return requestSearchResult(uri);
    }

    public List<User> getUserByPhoneNumber(String phoneNumber) {
        UriComponents uri = getUriCommonBuilder()
                .queryParam("phoneNumber", phoneNumber)
                .build();

        return requestSearchResult(uri);
    }

    private List<User> requestSearchResult(UriComponents uri) {
        ResponseEntity<List<User>> response = restTemplate.exchange(uri.toUriString(), GET, getHttpEntity(), new ParameterizedTypeReference<>(){});

        return response.getBody();
    }

    private UriComponentsBuilder getUriCommonBuilder() {
        return UriComponentsBuilder.fromHttpUrl(gatewayApiUrl)
                .queryParam(VERSION, V_0_1_0)
                .queryParam(FUNCTION, FIND);
    }

    private HttpEntity getHttpEntity() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return new HttpEntity(header);
    }
}
