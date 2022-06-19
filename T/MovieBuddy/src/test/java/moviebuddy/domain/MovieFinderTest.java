package moviebuddy.domain;

import moviebuddy.data.CsvMovieReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;
import org.junit.jupiter.api.extension.ExtendWith; // JUnit이 테스트 실행 전략을 확장할 때 사용
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension; // 스프링에서 제공하는 JUnit 지원 클래스로, JUnit이 테스트를 실행하는 과정에서 테스트가 필요로 하는 스프링 컨테이너를 구성하고 관리해줌
import org.springframework.test.context.ContextConfiguration; //SpringExtension이 스프링 테스트 컨테이너를 구성할 때, 빈 구성 정보를 불러들여 스프링 컨테이너를 만듦(하위의 ApplicationContext가 없어도된다! ->
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
// 스프링 컨테이너가 구성될 것이기 때문에 직접 스프링의 애플리케이션 컨텍스트를 생성하는 코드는 제거해도됨. 애플리케이션 컨텍스트로부터 빈을 얻는게 아니라 스프링이 제공하는 의존관계 주입 기능을 활용해서 필요한 의존성을 취득하면되니까!

import java.util.List;

import static moviebuddy.MovieBuddyProfile.CSV_MODE;
import static org.mockito.BDDMockito.given;

/*
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MovieBuddyFactory.class)*/
@ActiveProfiles(CSV_MODE)
@SpringJUnitConfig(MovieBuddyFactory.class) // 메타애노테이션 : 내부적으로 ExtendWith + ContextConfiguration 이있음.
public class MovieFinderTest {

	@Autowired
	MovieFinder movieFinder;

	@Test
	void NotEmpty_directedBy(){
		List<Movie> result = movieFinder.directedBy("Michael Bay");
		Assertions.assertEquals(3, result.size());
	}

	@Test
	void NotEmpty_releasedYearBy(){
		List<Movie> result = movieFinder.releasedYearBy(2015);
		Assertions.assertEquals(225,result.size());
	}


}
