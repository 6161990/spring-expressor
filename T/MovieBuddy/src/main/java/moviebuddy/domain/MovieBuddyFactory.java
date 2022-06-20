package moviebuddy.domain;

import com.github.benmanes.caffeine.cache.Caffeine;
import moviebuddy.data.CachingMovieReader;
import moviebuddy.data.CsvMovieReader;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.*;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@PropertySource("/application.properties")
@ComponentScan(basePackages = {"moviebuddy"})
@Import(MovieBuddyFactory.DataSourceModuleConfig.class)
public class MovieBuddyFactory {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("moviebuddy");

        return marshaller;
    }

    @Bean
    public CacheManager cacheManager(){
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder().expireAfterWrite(3, TimeUnit.SECONDS));

        return cacheManager;
    }

    @Configuration
    static class DataSourceModuleConfig{

        @Primary
        @Bean
        public MovieReader cachingMovieReader(CacheManager cacheManager, MovieReader target){
            return new CachingMovieReader(cacheManager, target);
        }
    }
}
