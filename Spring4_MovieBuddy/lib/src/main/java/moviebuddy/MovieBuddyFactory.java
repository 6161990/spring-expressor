package moviebuddy;

import moviebuddy.domain.CsvMovieReader;
import moviebuddy.domain.MovieFinder;

public class MovieBuddyFactory { //객체를 생성하고 구성하는 역할 
	
	public MovieFinder movieFinder() {
		return new MovieFinder(new CsvMovieReader());
	}

}
