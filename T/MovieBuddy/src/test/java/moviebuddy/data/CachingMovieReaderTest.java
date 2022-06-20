package moviebuddy.data;

import moviebuddy.domain.Movie;
import moviebuddy.domain.MovieReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import java.util.ArrayList;
import java.util.List;

import static moviebuddy.data.CachingMovieReader.CACHE_NAME;
public class CachingMovieReaderTest {

    @Test
    void caching() {
        CacheManager cacheManager = new ConcurrentMapCacheManager();
        MovieReader target = new DummyMovieReader();

        CachingMovieReader movieReader = new CachingMovieReader(cacheManager, target);
        Cache cache = cacheManager.getCache(CACHE_NAME);

        Assertions.assertNull(movieReader.getCachedData(cache));

        List<Movie> movies = movieReader.loadMovies();
        Assertions.assertNotNull(movieReader.getCachedData(cache));
        Assertions.assertSame(movieReader.loadMovies(), movies);
    }


    class DummyMovieReader implements MovieReader {

        @Override
        public List<Movie> loadMovies() {
            return new ArrayList<>();
        }
    }
}
