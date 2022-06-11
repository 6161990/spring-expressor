package moviebuddy.domain;

public class MovieBuddyFactory {

    public MovieFinder movieFinder() {
        return new MovieFinder(new CsvMovieReader());
    }
}
