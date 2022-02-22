package com.sp.fc.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class PaperUserTestApp {

    public static void main(String[] args) {
        SpringApplication.run(PaperUserTestApp.class, args);
    }

    //server쪽 application에서는 잘 스캔이되는데 test에서는 잘 스캔이 안되는 경우가 있어서 ,
    // 별도로 config bean을 만들고 설정함.
    @Configuration
    @ComponentScan("com.sp.fc.user")
    @EnableJpaRepositories(basePackages = {
            "com.sp.fc.user.repository"
    })
    @EntityScan(basePackages = {
            "com.sp.fc.user.domain"
    })
    class Config{

    }
}
