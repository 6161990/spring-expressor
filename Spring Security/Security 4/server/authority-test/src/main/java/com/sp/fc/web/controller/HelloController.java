package com.sp.fc.web.controller;

import com.sp.fc.web.service.SecurityMessageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final SecurityMessageService securityMessageService;

    public HelloController(SecurityMessageService securityMessageService) {
        this.securityMessageService = securityMessageService;
    }


/*

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/greeting")
    public String greeting(){
        return "hello";
    }*/

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/greeting/{name}")
    public String greeting(@PathVariable String name){
        return "hello "+ securityMessageService.message(name);
    }
}
