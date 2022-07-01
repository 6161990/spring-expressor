package moviebuddy.domain;

import moviebuddy.MovieBuddyFactory;
import moviebuddy.domain.JaxbMovieReader;
import moviebuddy.domain.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(MovieBuddyFactory.class)
class JaxbMovieReaderTest {

    @Autowired
    JaxbMovieReader sut;

    @Test
    void NotEmpty_LoadedMovies() {
        List<Movie> actual = sut.loadMovies();

        Assertions.assertEquals(1375, actual.size());
    }
}
