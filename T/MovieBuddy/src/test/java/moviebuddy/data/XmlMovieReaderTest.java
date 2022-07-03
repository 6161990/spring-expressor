package moviebuddy.data;

import moviebuddy.MovieBuddyFactory;
import moviebuddy.domain.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(MovieBuddyFactory.class)
class XmlMovieReaderTest {

    @Autowired
    XmlMovieReader sut;

    @Test
    void NotEmpty_LoadedMovies() {
        List<Movie> actual = sut.loadMovies();

        Assertions.assertEquals(1375, actual.size());
    }
}
