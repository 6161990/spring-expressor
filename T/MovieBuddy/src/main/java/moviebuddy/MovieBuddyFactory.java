package moviebuddy;

import moviebuddy.data.CsvMovieReader;
import moviebuddy.data.XmlMovieReader;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

@Configuration
@ComponentScan(basePackages = "moviebuddy")
@Import({MovieBuddyFactory.DataSourceModuleConfig.class})
public class MovieBuddyFactory {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("moviebuddy");

        return marshaller;
    }

    @Configuration
    static class DataSourceModuleConfig {

        private final Environment environment;

        DataSourceModuleConfig(Environment environment) {
            this.environment = environment;
        }

        @Profile(MovieBuddyProfile.CSV_MODE)
        @Bean
        public CsvMovieReader csvMovieReader() throws FileNotFoundException, URISyntaxException {
            CsvMovieReader movieReader = new CsvMovieReader();

            return movieReader;
        }

        @Profile(MovieBuddyProfile.XML_MODE)
        @Bean
        public XmlMovieReader xmlMovieReader(Unmarshaller unmarshaller){
            XmlMovieReader xmlMovieReader = new XmlMovieReader(unmarshaller);

            return xmlMovieReader;
        }
    }
}
