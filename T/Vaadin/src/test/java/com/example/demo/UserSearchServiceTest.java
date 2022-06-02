package com.example.demo;

import com.example.application.data.service.UserSearchService;
import com.example.application.data.views.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(value = {UserSearchService.class})
class UserSearchServiceTest {

    @Autowired
    private UserSearchService sut;

    @Autowired
    private MockRestServiceServer mockServer;

    @Value("${gateway.api.url}")
    private String gatewayApiUrl;
    private StringBuffer url;
    private String expectResult;

    @BeforeEach
    void setUp() {
        url = new StringBuffer(gatewayApiUrl);
        expectResult = "[{\"id\":\"1001\",\"name\":\"테스트\",\"phone\":\"01012345678\",\"email\":\"natest@trevari\",\"signedAt\":\"2022-02-17T05:11:02.249\",\"deletedAt\":\"2022-03-02T07:20:52.365\",\"inactive\":false,\"isBlock\":false}]";
    }

    @Test
    void restTemplate을_이용하여_검색결과를_받아올_수_있다() {
        String name = "테스트";
        StringBuffer url = appendUrl(name);

        mockServer.expect(requestTo(url.toString()))
                .andRespond(withSuccess(expectResult, MediaType.APPLICATION_JSON));

        List<User> response = sut.getUserByName(name);

        mockServer.verify();

        assertEquals(response.stream().findFirst().get().getUserId(), "1001");
        assertEquals(response.stream().findFirst().get().getUserName(), "테스트");
    }

    private StringBuffer appendUrl(String name) {
        return url.append("?v=0.1.0&f=find&name=")
                .append(URLEncoder.encode(name, StandardCharsets.UTF_8));
    }

}
