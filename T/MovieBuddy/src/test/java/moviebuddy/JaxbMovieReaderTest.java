package moviebuddy;

import moviebuddy.domain.JaxbMovieReader;
import moviebuddy.domain.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JaxbMovieReaderTest {

    @Test
    void NotEmpty_LoadedMovies() {
        JaxbMovieReader movieReader = new JaxbMovieReader();

        List<Movie> actual = movieReader.loadMovies();

        Assertions.assertEquals(1375, actual.size());
    }
}
