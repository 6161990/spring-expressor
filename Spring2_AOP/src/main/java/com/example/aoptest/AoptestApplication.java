package com.example.aoptest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Base64;

@EnableAspectJAutoProxy
@SpringBootApplication
public class AoptestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AoptestApplication.class, args);
        System.out.println(Base64.getEncoder().encodeToString("jenny@gmail.com".getBytes()));
    }

}
