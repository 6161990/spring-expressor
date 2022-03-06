package moviebuddy.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import moviebuddy.MovieBuddyFactory;

public class BeanScopeTests {

	@Test
	void Equals_MovieFinderBean() { // 싱글톤 스코프
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MovieBuddyFactory.class);
		MovieFinder movieFinder = applicationContext.getBean(MovieFinder.class);
		
		Assertions.assertEquals(movieFinder, applicationContext.getBean(MovieFinder.class));
	}
	
//	@Test
//	void NotEquals_MovieFinderBean() { // 프로토 타입 스코프 
//		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MovieBuddyFactory.class);
//		MovieFinder movieFinder = applicationContext.getBean(MovieFinder.class);
//		
//		Assertions.assertNotEquals(movieFinder, applicationContext.getBean(MovieFinder.class));
//	}
}
