package com.company.shop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //충돌이 날 수 있으므로 conpiguration은 제외 (excludeFilters), 예제(AppConfig.class)를 안전하게 유지하기 위해
)
public class AutoAppConfig {

}
