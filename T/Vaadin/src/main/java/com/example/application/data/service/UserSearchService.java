package com.example.application.data.service;

import com.example.application.data.views.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;


@Service
public class UserSearchService {

    private final RestTemplate restTemplate;
    private static final String V = "v";
    private static final String _0_1_0 = "0.1.0";
    private static final String F = "f";
    private static final String FIND = "find";

    @Value("${gateway.api.url}")
    private String gatewayApiUrl;

    public UserSearchService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public User getUserById(String userId) {
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(gatewayApiUrl)
                .path("/")
                .path(userId)
                .queryParam(V, _0_1_0)
                .build();

        ResponseEntity<User> response = restTemplate.exchange(uri.toUriString(), GET, getHttpEntity(), User.class);

        return response.getBody();
    }

    public List<User> getUserByName(String name)  {
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
                .queryParam(V, _0_1_0)
                .queryParam(F, FIND);
    }

    private HttpEntity getHttpEntity() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return new HttpEntity(header);
    }
}
