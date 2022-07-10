package pattern.abstractFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pattern.abstractFactory.LoadDaoFactory;
import pattern.abstractFactory.factory.MySqlDaoFactory;
import pattern.abstractFactory.factory.OracleDaoFactory;

@Configuration
public class AbstractFactoryConfigurations {

    @Bean
    public LoadDaoFactory loadDaoFactory(){
        return new LoadDaoFactory();
    }

    @Bean
    @Profile("mysql")
    public MySqlDaoFactory mySqlDaoFactory(){
        return new MySqlDaoFactory();
    }

    @Bean
    @Profile("oracle")
    public OracleDaoFactory oracleDaoFactory(){
        return new OracleDaoFactory();
    }
}
