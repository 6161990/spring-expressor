package moviebuddy.domain;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import static moviebuddy.domain.MovieBuddyFactory.DataSourceModuleConfig.movieReader;

@Configuration
@Import({MovieBuddyFactory.DataSourceModuleConfig.class, MovieBuddyFactory.DomainModuleConfig.class}) // 다른 클래에서 빈 구성정보를 가져오기위함.
//  @ImportResource("xml file location") XML 형식의 빈 구성정보 일 경우, 위치 지정
public class MovieBuddyFactory {

    @Configuration
    static class DataSourceModuleConfig {
        @Bean
        public static MovieReader movieReader(){
            return new CsvMovieReader();
        }
    }

    @Configuration
    static class DomainModuleConfig {
        @Bean // 의존 관계 주입 - 메소드 콜 방식
        public static MovieFinder movieFinder() {
            return new MovieFinder(movieReader());
        }

        /**
         *   @Bean // 의존 관계 주입 - 메소드 파라미터 방식
         *   @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
         *   public MovieFinder movieFinder(MovieReader movieReader) {
         *       return new MovieFinder(movieReader);
         *   }
         * */
    }





}
