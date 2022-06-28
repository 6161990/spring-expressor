package moviebuddy;

import moviebuddy.domain.CsvMovieReader;
import moviebuddy.domain.MovieFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieBuddyFactory {

    @Bean
    public MovieFinder movieFinder(){
        return new MovieFinder(new CsvMovieReader());
    }
    /**
     * 스프링에서는 객체 생성, 의존관계 설정, 사용 등을 애플리케이션 코드 대신 독립된 컨테이너가 담당한다.
     * → IoC = 스프링 컨테이너
     * */
}
