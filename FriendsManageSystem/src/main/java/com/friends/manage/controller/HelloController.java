package com.friends.manage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/api/helloworld")
    public String helloWorld(){
        return "HelloWorld";
    }
}
