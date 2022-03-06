package moviebuddy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import moviebuddy.domain.CsvMovieReader;
import moviebuddy.domain.MovieFinder;
import moviebuddy.domain.MovieReader;

@Configuration // 빈 구성정보 - Configuration 메타데이터로 사용함을 선언 
public class MovieBuddyFactory { //객체를 생성하고 구성하는 역할 
	
	@Bean
	public MovieReader movieReader() { // 등록된 또 하나의 빈 
		return new CsvMovieReader();
	}
	
	@Bean // 어떤 빈이 있는가 
	public MovieFinder movieFinder() {
		return new MovieFinder(movieReader()); // 메소드 호출 방식 
	}

	/**
	 * 
	 *  @Bean  
		public MovieFinder movieFinder(MovieReader movieReader) {
			return new MovieFinder(movieReader);  
		}
		 파라미터 호출 방식 :  MovieFinder 빈을 등록하려고 할 때,
		 * 메서드의 파라미를 보고 MovieFinder을 등록하려면 MovieReader가 필요하겠구나 하고 
		 * 스스로 내부에 MovieReader빈이 있는지 확인한다.
	 * 
	 */
}
