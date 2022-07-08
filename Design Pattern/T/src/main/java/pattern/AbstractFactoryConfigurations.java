package pattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pattern.abstractFactory.LoadDaoFactory;

@Configuration
public class AbstractFactoryConfigurations {

    @Bean
    public LoadDaoFactory loadDaoFactory(){
        return new LoadDaoFactory();
    }
}
