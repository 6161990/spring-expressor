package com.sp.fc.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//실제 applicaion에서 해당 모듈에 대한 bean과 특징들을 scan해가기위한 정보들을 취합해놓음
@Configuration
@ComponentScan("com.sp.fc.user")
@EnableJpaRepositories(basePackages = {
        "com.sp.fc.user.repository"
})
@EntityScan(basePackages = {
        "com.sp.fc.user.domain"
})
public class PaperUserModule {
}
