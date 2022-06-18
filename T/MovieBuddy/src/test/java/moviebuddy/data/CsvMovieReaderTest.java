package moviebuddy.data;

import moviebuddy.domain.MovieBuddyFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.FileNotFoundException;

import static moviebuddy.MovieBuddyProfile.CSV_MODE;

@ActiveProfiles(CSV_MODE)
@SpringJUnitConfig(MovieBuddyFactory.class)
class CsvMovieReaderTest {

    @Autowired
    CsvMovieReader movieReader;

    @Test
    void Valid_Metadata() throws Exception {
        movieReader.afterPropertiesSet();
    }

    @Test
    void Invalid_Metadata() {

        Assertions.assertThrows(FileNotFoundException.class, () ->{
           movieReader.setMetadata("invalid");
           movieReader.afterPropertiesSet();
        });
    }

}