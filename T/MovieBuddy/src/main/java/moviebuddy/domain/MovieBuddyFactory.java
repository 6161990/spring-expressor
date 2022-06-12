package moviebuddy.domain;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MovieBuddyFactory {

    @Bean
    public MovieReader movieReader(){
        return new CsvMovieReader();
    }

    @Bean // 의존 관계 주입 - 메소드 콜 방식
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public MovieFinder movieFinder() {
        return new MovieFinder(movieReader());
    }

    /**
     *   @Bean // 의존 관계 주입 - 메소드 파라미터 방식
     *   public MovieFinder movieFinder(MovieReader movieReader) {
     *       return new MovieFinder(movieReader);
     *   }
     * */

}
