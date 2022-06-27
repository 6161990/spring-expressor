package moviebuddy;

import moviebuddy.domain.Movie;
import moviebuddy.domain.MovieFinder;

import java.util.List;

public class MovieBuddyApplicationTest {

	public static void main(String[] args) {
		MovieFinder movieFinder = new MovieFinder();

		List<Movie> result = movieFinder.directedBy("Michael Bay");
		assertEquals(3, result.size());

        result = movieFinder.releasedYearBy(2015);
        assertEquals(225, result.size());
	}
	
	static void assertEquals(long expected, long actual) {
		if (expected != actual) {
			throw new RuntimeException(String.format("actual(%d) is different from the expected(%d)", actual, expected));			
		}
	}
	
}
