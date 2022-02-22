package com.friends.manage.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.friends.manage.configuration.serializer.BirthdaySerializer;
import com.friends.manage.domain.dto.Birthday;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

//해당 seializer를 스프링 컨테이너에 mapping
@Configuration
public class JsonConfig {

    //3. objectMapper를 MessageConverter에 넣어주기
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper){
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    //2. serializer mapping된 모듈을 objecetMapper에 넣어주기
    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new BirthdayModule());
        objectMapper.registerModule(new JavaTimeModule());

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return objectMapper;
    }

    //1. 모듈에 만들어 놓은 serializer mapping 시켜주기
    static class BirthdayModule extends SimpleModule{
        BirthdayModule(){
            super();
            addSerializer(Birthday.class, new BirthdaySerializer());
        }
    }
}
