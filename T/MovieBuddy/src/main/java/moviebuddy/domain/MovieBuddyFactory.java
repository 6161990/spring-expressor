package moviebuddy.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieBuddyFactory {

    @Bean
    public MovieFinder movieFinder() {
        return new MovieFinder(new CsvMovieReader());
    }
}
