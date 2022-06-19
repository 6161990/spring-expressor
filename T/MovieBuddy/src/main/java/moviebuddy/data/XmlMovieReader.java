package moviebuddy.data;

import lombok.Getter;
import lombok.Setter;
import moviebuddy.ApplicationException;
import moviebuddy.domain.Movie;
import moviebuddy.domain.MovieReader;
import org.springframework.context.annotation.Profile;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Repository;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static moviebuddy.MovieBuddyProfile.XML_MODE;

@Profile(XML_MODE)
@Repository
public class XmlMovieReader extends AbstractMetadataResourceMovieReader implements MovieReader {

    private final Unmarshaller unmarshaller;

    public XmlMovieReader(Unmarshaller unmarshaller) {
        this.unmarshaller = Objects.requireNonNull(unmarshaller);
    }


    @Override
    public List<Movie> loadMovies() {
        try {
            final InputStream content = getMetadataSource().getInputStream();
            final Source source = new StreamSource(content);
            final MovieMetadata metadata = (MovieMetadata) unmarshaller.unmarshal(source);

            return metadata.toMovies();
        } catch (IOException e) {
            throw new ApplicationException("failed to load movies data");
        }
    }

    @Getter
    @Setter
    @XmlRootElement(name = "moviemetadata")
    public static class MovieMetadata {
        private List<MovieData> movies;

        public List<Movie> toMovies() {
            return movies.stream().map(MovieData::toMovie).collect(Collectors.toList());
        }
    }

    @Getter
    @Setter
    public static class MovieData {
        private String title;
        private List<String> genres;
        private String language;
        private String country;
        private int releaseYear;
        private String director;
        private List<String> actors;
        private URL imdbLink;
        private String watchedDate;

        public Movie toMovie() {
            return Movie.of(title, genres, language, country, releaseYear, director, actors, imdbLink, watchedDate);
        }
    }
}
