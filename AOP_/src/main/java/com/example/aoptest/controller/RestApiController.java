package com.example.aoptest.controller;


import com.example.aoptest.annotation.Decode;
import com.example.aoptest.annotation.Timer;
import com.example.aoptest.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name){
        System.out.println("[Controller   @GetMapping]");
        System.out.println("get method: "+id);
        System.out.println("get method: "+name);
        System.out.println(" ");
        return id+" "+name;
    }


    @PostMapping("/post")
    public User post(@RequestBody User user){
        System.out.println("[Controller  @PostMapping]");
        System.out.println("post metod :" +user);
        System.out.println(" ");
        return user;
    }

    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {
        //db logic
        Thread.sleep(1000*2);
    }

    @Decode
    @PutMapping("/put")
    public User put(@RequestBody User user){
        System.out.println("[Controller  @PutMapping]");
        System.out.println("user "+user);
        return user;
    }

}
