package moviebuddy.domain;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import moviebuddy.MovieBuddyFactory;

@ExtendWith(SpringExtension.class) //JUnit과 Spring 의 만남
@ContextConfiguration(classes=MovieBuddyFactory.class) // 하위에 있던 애플리케이션 컨텍스트 생성을없애줘도된다. 이제 테스트도 스프링컨텍스트를 이용하니까! 
public class MovieFinderTest { 
			
	final MovieFinder movieFinder;
	
	@Autowired
	MovieFinderTest(MovieFinder movieFinder){
		this.movieFinder = movieFinder;
	}

	
	@Test
	public void NotEmpty_directedBy() {
		List<Movie> result = movieFinder.directedBy("Michael Bay");
		Assertions.assertEquals(3, result.size());
	}
	
		
		
	@Test
	public void NotEmpty_releasedYearBy() {
		List<Movie> result = movieFinder.releasedYearBy(2015);
        Assertions.assertEquals(225, result.size());
	}
	
}
