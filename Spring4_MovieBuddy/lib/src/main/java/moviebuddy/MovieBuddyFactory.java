package moviebuddy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import moviebuddy.domain.CsvMovieReader;
import moviebuddy.domain.MovieFinder;

@Configuration // 빈 구성정보 - Configuration 메타데이터로 사용함을 선언 
public class MovieBuddyFactory { //객체를 생성하고 구성하는 역할 
	
	@Bean // 어떤 빈이 있는가 
	public MovieFinder movieFinder() {
		return new MovieFinder(new CsvMovieReader());
	}

}
