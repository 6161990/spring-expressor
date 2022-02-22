package yoon.spring.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableBatchProcessing// 스프링 배치를 이용하겠다.
public class YoonSpringBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(YoonSpringBatchApplication.class, args);
    }

}
