package moviebuddy.domain;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import moviebuddy.ApplicationException;
import moviebuddy.util.FileSystemUtils;

public class MovieFinder {
	
	/**
     * 저장된 영화 목록에서 감독으로 영화를 검색한다.
     * 
     * @param directedBy 감독
     * @return 검색된 영화 목록
     */
    public List<Movie> directedBy(String directedBy) {
        return loadMovies().stream()
                           .filter(it -> it.getDirector().toLowerCase().contains(directedBy.toLowerCase()))
                           .collect(Collectors.toList());
    }

    /**
     * 저장된 영화 목록에서 개봉년도로 영화를 검색한다.
     * 
     * @param releasedYearBy
     * @return 검색된 영화 목록
     */
    public List<Movie> releasedYearBy(int releasedYearBy) {
        return loadMovies().stream()
                           .filter(it -> Objects.equals(it.getReleaseYear(), releasedYearBy))
                           .collect(Collectors.toList());
    }

    /**
     * 영화 메타데이터를 읽어 저장된 영화 목록을 불러온다.
     * 
     * @return 불러온 영화 목록
     */
    public List<Movie> loadMovies() {
    	// 메타데이터 형식별로 로직을 작성하고 적합 형식을 선택하는 if 분기문 로직 
    	// 근데 또 다른 형식이 추가된다면? -> 새로운 비즈니스 요구사항이 늘어날 때마다 코드를 수정해야한다면 bug 가 발생할 확률이 매우높다.
    	// 코드 수정 시, 불안하고 두렵게 됨. -> 오늘의 요구사항을 완성시면서 내일의 변경을 수용할 수 있도록 어플리케이션을 설계해야한다. 
    	if(mode == "CSV") {
    		return loadMoviesFromCSV();
    	} else if(mode == "XML") {
    		return loadMoviesFromXml();
    	}
        try {
            final URI resourceUri = ClassLoader.getSystemResource("movie_metadata.csv").toURI();
            final Path data = Path.of(FileSystemUtils.checkFileSystem(resourceUri));
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
                } catch (IOException error) {
                    throw new ApplicationException("mapping csv to object failed.", error);
                }
            };

            return Files.readAllLines(data, StandardCharsets.UTF_8)
                        .stream()
                        .skip(1)
                        .map(mapCsv)
                        .collect(Collectors.toList());
        } catch (IOException | URISyntaxException error) {
            throw new ApplicationException("failed to load movies data.", error);
        }

    }
    
    //CSV 메타데이터로 영화목록을 불러오기 
    List<Movie> loadMoviesFromCSV() {
    	return Collections.emptyList();
    }
    
    //XML 메타데이터로 영화목록을 불러오기     
    List<Movie> loadMoviesFromXml() {
    	return Collections.emptyList();
    }
    
}
