package com.example.locdi;

import com.example.locdi.ch5ExplainForloC.ApplicationContextProvider;
import com.example.locdi.ch5ExplainForloC.Base64Encoder;
import com.example.locdi.ch5ExplainForloC.Encoder;
import com.example.locdi.ch5ExplainForloC.UrlEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class LocdiApplication {

    public static void main(String[] args) {

        SpringApplication.run(LocdiApplication.class, args);

        ApplicationContext context = ApplicationContextProvider.getContext();

        Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
        UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);

      //  Encoder encoder = new Encoder(base64Encoder);

      //  @ Configuration으로 Bean 직접 주입할 시,
         Encoder encoder = context.getBean("urlEncode", Encoder.class);

        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";
        String result = encoder.encode(url);
        System.out.println(result);

        encoder.setIEncoder(urlEncoder);
        result = encoder.encode(url);
        System.out.println(result);

    }

    @Configuration
    class AppConfig {

        @Bean("base64Encode")
        public Encoder encoder(Base64Encoder base64Encoder) {
            return new Encoder(base64Encoder);
        }

        @Bean("urlEncode")
        public Encoder encoder(UrlEncoder urlEncoder) {
            return new Encoder(urlEncoder);
        }

    }
    

}
