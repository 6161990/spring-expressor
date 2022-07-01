package moviebuddy.domain;

import moviebuddy.MovieBuddyFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MovieBuddyFactory.class)
class MovieFinderTest {

	@Autowired
	MovieFinder sut;

	@Test
	void NotEmpty_directedBy() {
		List<Movie> result = sut.directedBy("Michael Bay");
		Assertions.assertEquals(3, result.size());
	}

	@Test
	void NotEmpty_ReleasedYearBy() {
		List<Movie> result = sut.releasedYearBy(2015);
		Assertions.assertEquals(225, result.size());
	}
	
}
