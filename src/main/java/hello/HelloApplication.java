package hello;

import ch5ExplainForloC.ApplicationContextProvider;
import ch5ExplainForloC.Base64Encoder;
import ch5ExplainForloC.Encoder;
import ch5ExplainForloC.UrlEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HelloApplication {

    public static void main(String[] args) {

        SpringApplication.run(HelloApplication.class, args);
//
//        ApplicationContext context = ApplicationContextProvider.getContext();
//
//        Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
//        UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);
//
//        Encoder encoder = new Encoder(base64Encoder);
//        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";
//        String result = encoder.encode(url);
//        System.out.println(result);
//
//        encoder.setIEncoder(urlEncoder);
//        result = encoder.encode(url);
//        System.out.println(result);
    }

}
