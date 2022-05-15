package moviebuddy.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import moviebuddy.ApplicationException;
import moviebuddy.MovieBuddyProfile;
import moviebuddy.domain.Movie;
import moviebuddy.domain.MovieReader;

//@Component 얘를 사용해도 됨! 
@Repository("csvMovieReader") // -> 데이터 접근 기술이 사용되는 빈을 정의 할 때 사용. 같은 타입으로 등록된 Bean이 2개일 때, 파라미터 이름을 지정해준다.
@Profile(MovieBuddyProfile.CSV_MODE)
public class CsvMovieReader extends AbstractMetadataResourceMovieReader implements MovieReader {
	
	/**
	 * 영화 메타데이터를 읽어 저장된 영화 목록을 불러온다.
	 * gradle은 빌드 시점에 src/main/java, src/main/resource 폴더를 묶어서 패키징하게 된다. 
	 * ClassLoader.getSystemResource 통해서 클래스 패스 경로에 있는 메타데이터를 쉽게 취득할 수 있다. 
	 * -> 클래스 패스가 아닌 다른 경로로 바꾸려면 코드의 변경이 있어야하는 상태. 
	 * -> 요구사항 2. 를 할 차례,,,! 
	 * -> 메타데이터 위치를 결정하는 것과 메타데이터를 읽는 행위도 변화의 이유와 시기가 다르기 때문에 분리해야한다.
	 * -> 외부에서 메타데이터 위치를 결정하면된다.  
	 * @return 불러온 영화 목록
	 */
	
	
	@Override
	public List<Movie> loadMovies() {

		try {
			/**final URI resourceUri = ClassLoader.getSystemResource(getMetadata()).toURI(); // getSystemResource -> 메타데이터의 경로 찾기
			final Path data = Path.of(FileSystemUtils.checkFileSystem(resourceUri)); // 자바의 NIO API인 Path와 Files라는 API를 통해서 메타데이터 내용 읽어들이*/
			final InputStream content = getMetadataResource().getInputStream();
			final Function<String, Movie> mapCsv = csv -> {
				try {
					// split with comma
					String[] values = csv.split(",");
		
					String title = values[0];
                    List<String> genres = Arrays.asList(values[1].split("\\|"));
                    String language = values[2].trim();
                    String country = values[3].trim();
                    int releaseYear = Integer.valueOf(values[4].trim());
                    String director = values[5].trim();
                    List<String> actors = Arrays.asList(values[6].split("\\|"));
                    URL imdbLink = new URL(values[7].trim());
                    String watchedDate = values[8]; 
                    
                    return Movie.of(title, genres, language, country, releaseYear, director, actors, imdbLink, watchedDate);
				} catch(IOException error) {
					throw new ApplicationException("mapping csv to object failed.", error);
				}
			};

			/**return Files.readAllLines(data, StandardCharsets.UTF_8)
						.stream()*/
			return new BufferedReader(new InputStreamReader(content, StandardCharsets.UTF_8))
						.lines()
						.skip(1)
						.map(mapCsv)
						.collect(Collectors.toList());
		} catch(IOException error) {
			throw new ApplicationException("failed to load movies data.", error);
		}

	}

}
