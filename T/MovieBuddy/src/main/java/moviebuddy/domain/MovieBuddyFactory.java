package moviebuddy.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"moviebuddy"}) // MovieBuddyFactory의 패키지와 같은 레벨을 모두 탐지함.
@Import({MovieBuddyFactory.DataSourceModuleConfig.class, MovieBuddyFactory.DomainModuleConfig.class}) // 다른 클래에서 빈 구성정보를 가져오기위함.
public class MovieBuddyFactory {

    @Configuration
    static class DataSourceModuleConfig {

    }

    @Configuration
    static class DomainModuleConfig {
        @Bean // 의존 관계 주입 - 메소드 콜 방식
        public MovieFinder movieFinder(MovieReader movieReader) {
            return new MovieFinder(movieReader);
        }

    }

}
