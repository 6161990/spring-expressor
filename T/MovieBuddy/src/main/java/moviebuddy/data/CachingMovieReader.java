package moviebuddy.data;

import moviebuddy.domain.Movie;
import moviebuddy.domain.MovieReader;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.List;
import java.util.Objects;

public class CachingMovieReader implements MovieReader {

    private final CacheManager cacheManager;
    private final MovieReader movieReader;

    static final String CACHE_NAME  = CachingMovieReader.class.getName();
    static final String CACHE_KEY = "movies";

    public CachingMovieReader(CacheManager cacheManager, MovieReader movieReader) {
        this.cacheManager = Objects.requireNonNull(cacheManager);
        this.movieReader = Objects.requireNonNull(movieReader);
    }

    @Override
    public List<Movie> loadMovies() {
        Cache cache = cacheManager.getCache(CACHE_NAME);
        List<Movie> movies = getCachedData(cache);
        if(Objects.nonNull(movies)){
            return movies;
        }

        movies = movieReader.loadMovies();
        cache.put(CACHE_KEY, movies);

        return movies;
    }

    public List<Movie> getCachedData(Cache cache) {
        return cache.get(CACHE_KEY, List.class);
    }
}
