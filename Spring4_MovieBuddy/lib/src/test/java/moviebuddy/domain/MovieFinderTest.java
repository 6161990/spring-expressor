package moviebuddy.domain;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import moviebuddy.MovieBuddyFactory;

/**
 * @author springrunner.kr@gmail.com
 */
public class MovieFinderTest {

	final ApplicationContext applicationContext = 
			new AnnotationConfigApplicationContext(MovieBuddyFactory.class); // 애플리케이션 컨텍스트 생성 -> 어노테이션 컨텍스트 정보로
	final MovieFinder movieFinder = applicationContext.getBean(MovieFinder.class); //  해당 클래스 구성정보를 가져온다.

	
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
