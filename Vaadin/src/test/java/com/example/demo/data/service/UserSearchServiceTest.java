package com.example.demo.data.service;

import com.example.application.data.service.RestTemplateConfig;
import com.example.application.data.service.UserSearchService;
import com.example.application.data.views.User;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ContextConfiguration(classes = {RestTemplateConfig.class, UserSearchService.class})
@RestClientTest(value = {UserSearchService.class})
class UserSearchServiceTest {

    private static final String NAME_PARAM = "name";
    private static final String PHONE_NUMBER_PARAM = "phoneNumber";
    private static final String NAME = "테스트";
    private static final String USER_ID = "id";
    private static final String PHONE_NUMBER = "01028810909";

    @Autowired
    private UserSearchService sut;

    @Autowired
    private MockRestServiceServer mockServer;

    @Value("${gateway.api.url}")
    private String gatewayApiUrl;
    private StringBuffer url;
    private String expectResult;
    private String expectResults;

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.createServer(sut.restTemplate);
        url = new StringBuffer(gatewayApiUrl);
        expectResult = String.format("{\"id\":\"%s\",\"name\":\"%s\",\"phone\":\"%s\",\"email\":\"natest@trevari\",\"signedAt\":\"2022-02-17T05:11:02.249\",\"deletedAt\":\"2022-03-02T07:20:52.365\",\"inactive\":false,\"isBlock\":false}",
                USER_ID, NAME, PHONE_NUMBER);
        expectResults = Lists.newArrayList(expectResult).toString();
    }

    @Test
    void ID_를_조회할_수_있다() {
        mockServer.expect(requestTo(appendUrl(USER_ID)))
                .andRespond(withSuccess(expectResult, MediaType.APPLICATION_JSON));

        User response = sut.getUserById(USER_ID);

        assertEquals(response.getUserId(), USER_ID);
    }

    @Test
    void NAME_을_조회할_수_있다() {
        mockServer.expect(requestTo(appendUrlByParam(NAME_PARAM, NAME)))
                .andRespond(withSuccess(expectResults, MediaType.APPLICATION_JSON));

        List<User> response = sut.getUserByName(NAME);

        assertEquals(response.stream().findFirst().get().getUserName(), NAME);
    }

    @Test
    void PHONE_NUMBER_를_조회할_수_있다() {
        mockServer.expect(requestTo(appendUrlByParam(PHONE_NUMBER_PARAM, PHONE_NUMBER)))
                .andRespond(withSuccess(expectResults, MediaType.APPLICATION_JSON));

        List<User> response = sut.getUserByPhoneNumber(PHONE_NUMBER);

        assertEquals(response.stream().findFirst().get().getPhoneNumber(), PHONE_NUMBER);
    }

    private String appendUrlByParam(String param, String value) {
        String PARAM = URLEncoder.encode(param, StandardCharsets.UTF_8);
        String VALUE = URLEncoder.encode(value, StandardCharsets.UTF_8);

        return url.append(String.format("?v=0.1.0&f=find&%s=%s", PARAM, VALUE))
                .toString();
    }

    private String appendUrl(String id) {
        String ID = URLEncoder.encode(id, StandardCharsets.UTF_8);

        return url.append(String.format("/%s?v=0.1.0", ID))
                .toString();
    }

}

