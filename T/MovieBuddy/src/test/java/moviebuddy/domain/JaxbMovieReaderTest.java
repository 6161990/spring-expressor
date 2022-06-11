package moviebuddy.domain;

import java.util.List;

class JaxbMovieReaderTest {

    public static void main(String[] args) {
        JaxbMovieReader movieReader = new JaxbMovieReader();

        List<Movie> movies = movieReader.loadMovies();
        MovieFinderTest.assertEquals(1375, movies.size());
    }
}