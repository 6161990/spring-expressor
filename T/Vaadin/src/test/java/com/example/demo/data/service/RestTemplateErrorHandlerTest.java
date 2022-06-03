package com.example.demo.data.service;

import com.example.application.data.service.RestTemplateConfig;
import com.example.application.data.service.UserSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RestClientTest(value = {UserSearchService.class})
@ContextConfiguration(classes = {RestTemplateConfig.class, UserSearchService.class})
public class RestTemplateErrorHandlerTest {

    @Autowired
    private UserSearchService userSearchService;

    @Value("${gateway.api.url}")
    private String gatewayApiUrl;

    private ResponseActions responseActions;

    @BeforeEach
    public void setUp() {
        responseActions = MockRestServiceServer.createServer(userSearchService.restTemplate)
                .expect(requestTo(gatewayApiUrl+"/test?v=0.1.0"))
                .andExpect(method(HttpMethod.GET));
    }

    @Test
    void API요청시_500에러가_발생하면_HttpServerErrorException을_던진다() {
        responseActions
                .andRespond(withStatus(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThatThrownBy(() -> userSearchService.getUserById("test")).isInstanceOf(HttpServerErrorException.class);
    }

    @Test
    void API요청시_400에러가_발생하면_HttpClientErrorException을_던진다() {
        responseActions
                .andRespond(withStatus(HttpStatus.BAD_REQUEST));

        assertThatThrownBy(() -> userSearchService.getUserById("test")).isInstanceOf(HttpClientErrorException.class);
    }
}
