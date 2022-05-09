package moviebuddy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration // 빈 구성정보 - Configuration 메타데이터로 사용함을 선언
@ComponentScan
@PropertySource("/application.properties")
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
/**		
		private final Environment environment;
		
		@Autowired
		public DataSourceModuleConfig(Environment environment) {
			this.environment = environment;
		}
		
		@Profile(MovieBuddyProfile.CSV_MODE)
		@Bean // 빈을 등록하면서 메타데이터 위치를 넘겨주도록 
		public CsvMovieReader csvMovieReader() throws FileNotFoundException, URISyntaxException {
			CsvMovieReader movieReader = new CsvMovieReader();
			
			// 애플리케이션 외부 설정파일이나 시스템 환경변수 등에서 설정정보를 작성해둔 후에 애플리케이션이 실행될 때 설정 정보를 읽어 메타데이터 위치를 설정
			//movieReader.setMetadata(environment.getProperty("movie.metadata"));
			
			return movieReader;
		}
		
		@Profile(MovieBuddyProfile.XML_MODE)
		@Bean 
		public XmlMovieReader xmlMovieReader(Unmarshaller unmarshaller) {
			XmlMovieReader xmlMovieReader = new XmlMovieReader(unmarshaller);
			//xmlMovieReader.setMetadata(environment.getProperty("movie.metadata"));
			
			return xmlMovieReader;
		}
			
	

	
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
}
