package moviebuddy;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import moviebuddy.data.CsvMovieReader;
import moviebuddy.domain.MovieFinder;
import moviebuddy.domain.MovieReader;

@Configuration // 빈 구성정보 - Configuration 메타데이터로 사용함을 선언
@ComponentScan
@Import({MovieBuddyFactory.DataSourceModuleConfig.class, MovieBuddyFactory.DomainModuleConfig.class}) // 다른 클래에서 빈 구성정보를 가져오기위함.
//  @ImportResource("xml file location")
public class MovieBuddyFactory { //객체를 생성하고 구성하는 역할 
	
	@Bean // 스프링 OXM 모듈 구현체 : Jaxb2Marshaller
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("moviebuddy"); 
		// 자동 스캔 탐지 기법과 비슷하게 지정된 패키지에서 XML을 자바 객체로 변환시 사용할 클래스를 찾아서 사용한다. 
		
		return marshaller;
	}
	
	
	@Configuration
	static class DomainModuleConfig {
		
	}
	
	@Configuration
	static class DataSourceModuleConfig {
		
		@Profile(MovieBuddyProfile.CSV_MODE)
		@Bean // 빈을 등록하면서 메타데이터 위치를 넘겨주도록 
		public CsvMovieReader csvMovieReader() throws FileNotFoundException, URISyntaxException {
			CsvMovieReader movieReader = new CsvMovieReader();
			movieReader.setMetadata("movie_metadata.csv");
			
			return movieReader;
		}
		
			
	}

	/**
	   메소드 호출 방식과 파라미터 호출 방식 
	 * @Bean
		//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // 빈요청을 받을 때마다 새로운 객체를 생 
		public MovieFinder movieFinder() {
			return new MovieFinder(movieReader()); // 메소드 호출 방식 
		}
		
		
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
