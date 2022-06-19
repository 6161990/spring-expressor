package moviebuddy.domain;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import moviebuddy.data.CsvMovieReader;
import org.springframework.context.annotation.*;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@PropertySource("/application.properties")
@ComponentScan(basePackages = {"moviebuddy"})
public class MovieBuddyFactory {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("moviebuddy");

        return marshaller;
    }

    @Configuration
    static class DataSourceModuleConfig{

        @Bean
        public CsvMovieReader csvMovieReader(){
            Cache<String, List<Movie>> cache = Caffeine.newBuilder()
                    .expireAfterWrite(3, TimeUnit.SECONDS)
                    .build();

            return new CsvMovieReader(cache);
        }
    }
}
