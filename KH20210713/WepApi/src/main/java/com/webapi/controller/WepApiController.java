package com.webapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapi.model.KimBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
public class WepApiController {

    @RequestMapping("hello")
    private String hello() {
        return "SpringBoot!";
    }

    @RequestMapping("/test/{param}")
    private String testPathVariable(@PathVariable("param") String param) {
        log.info(param);
        return "받은 파라미터 " + param;
    }

    @RequestMapping("/test")
    private String testRequestParam(@RequestParam("param") String param) {
        log.info(param);
        return "받은 요청파라미터 " + param;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    private String testRequestBody(@RequestBody String body) {
        log.info(body);
        return "요청 바디 파라미터: " + body;
    }

    //httpMethod 지정
    @RequestMapping(value = "/res", method = RequestMethod.POST)
    private String create(@RequestBody String data) {
        return "등록";
    }

    @RequestMapping(value = "/res/{id}", method = RequestMethod.GET)
    private String read(@PathVariable String id) {
        return "조회";
    }

    @RequestMapping(value = "/res/{id}", method = RequestMethod.DELETE)
    private String delete(@PathVariable String id) {
        return "삭제";
    }

    @RequestMapping(value = "/res/{id}", method = RequestMethod.PUT)
    private String update(@PathVariable String id, @RequestBody String data) {
        return "변경";
    }

    //리턴값을 Bean으로 처리
    /*public static class KimBean {
        private String kim;
        private int age;

        public KimBean(String s, int i) {
            log.info(s);
            log.info(String.valueOf(i));
        }
    }

     */

    @RequestMapping("/kim")
    public KimBean  kim() {
        return new KimBean("안녕!", 23);
    }


    @RequestMapping(value="/kim2",produces= MediaType.APPLICATION_JSON_VALUE)
    public String string() throws Exception{
        KimBean bean = new KimBean("안녕!!!!!", 123);
        String json = new ObjectMapper().writeValueAsString(bean);
        return json;

    }

    @RequestMapping("/kim3")
    public Map<String, Object> map() {
        Map<String, Object> map = new HashMap<>();
        map.put("홍길동", "조선");
        map.put("이재명", 2021);

        return map;
    }

    @RequestMapping(value="/kim4", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Resource file() {
        return new FileSystemResource(new File("C:\\Users\\Public\\Pictures\\1.png"));
    }

    @RequestMapping("/test/ex")
    public String textException() throws Exception {
        throw new RuntimeException("오류");
    }


}