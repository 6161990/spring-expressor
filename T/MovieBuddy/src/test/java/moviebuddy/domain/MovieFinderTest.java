package moviebuddy.domain;

import moviebuddy.MovieBuddyFactory;
import moviebuddy.MovieBuddyProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(classes = MovieBuddyFactory.class)
@ActiveProfiles(MovieBuddyProfile.CSV_MODE)
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
