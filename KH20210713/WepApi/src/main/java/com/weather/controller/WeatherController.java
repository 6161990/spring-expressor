package com.weather.controller;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api")
public class WeatherController {


    @RequestMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
    private String call(){
        RestTemplate rest = new RestTemplate();

        // https://openweathermap.org/current
        // 회원가입후 , 인증키를 받은 후 받은 인증키를 appid 변수값에 적용
        final String city = "seoul";
        final String endpoint = "http://api.openweathermap.org/data/2.5/wearher";
        final String appid = "0c0937c1a3207e719ec18739478a3859";
        final String url = endpoint +"?q="+ city + "&appid=" +appid;

        ResponseEntity<String> response = rest.getForEntity(url, String.class);
        String json= response.getBody();
        return decode(json);
    }

    // decoding 은 한글이 2바이트 문자열이기 때문에 문자가 깨지는 것을 방지함
    private static String decode(String string){
        return StringEscapeUtils.unescapeJava(string);
    }
}
