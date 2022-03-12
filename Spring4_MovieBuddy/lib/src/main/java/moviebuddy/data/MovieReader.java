package moviebuddy.data;

import java.util.List;

import moviebuddy.domain.Movie;

public interface MovieReader {
	
	List<Movie> loadMovies();

}
