package moviebuddy.data;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;

public class CsvMovieReaderTest {
	
	@Test
	void Valid_Metadata() throws Exception {
		AbstractFileSystemMovieReader csvMovieReader = new CsvMovieReader();
		csvMovieReader.setMetadata("movie_metadata.csv");
		csvMovieReader.setResourceLoader(new DefaultResourceLoader());
		
		csvMovieReader.afterPropertiesSet();
	}
	
	@Test
	void Invalid_Metadata() {
		AbstractFileSystemMovieReader csvMovieReader = new CsvMovieReader();
		csvMovieReader.setResourceLoader(new DefaultResourceLoader());
		
		Assertions.assertThrows(FileNotFoundException.class, () -> {
			csvMovieReader.setMetadata("invalid");
			
			csvMovieReader.afterPropertiesSet();
		});
	}

}
