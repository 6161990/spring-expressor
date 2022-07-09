package pattern.abstractFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import pattern.abstractFactory.factory.DaoFactory;
import pattern.abstractFactory.factory.MySqlDaoFactory;
import pattern.abstractFactory.factory.OracleDaoFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class LoadDaoFactory {

    @Autowired
    private DaoFactory daoFactory;

    @Autowired
    private Environment environment;

    @Autowired
    private ResourceLoader resourceLoader;

    public DaoFactory determineConcreteDaoFactory() throws Exception {
        return daoFactory;
    }

    public DaoFactory determineConcreteDaoFactory_dynamic() throws Exception {
        DaoFactory daoFactory;

        String activeProfiles = Arrays.stream(environment.getActiveProfiles())
                .findFirst().orElse("NO ACTIVE PROFILES");

        if(activeProfiles.equals("mysql")){
            daoFactory = new MySqlDaoFactory();
        }else if(activeProfiles.equals("oracle")){
            daoFactory = new OracleDaoFactory();
        }else {
            throw new Exception("Do Not Exist DB Type");
        }

        return daoFactory;
    }


    public DaoFactory determineConcreteDaoFactory_static() throws Exception {
        DaoFactory daoFactory;

        Map<String, String> clientDBType = getClientDBType();

        if(clientDBType.get("DBTYPE").equals("MYSQL")){
            daoFactory = new MySqlDaoFactory();
        }else if(clientDBType.get("DBTYPE").equals("ORACLE")){
            daoFactory = new OracleDaoFactory();
        }else {
            throw new Exception("Do Not Exist DB Type");
        }

        return daoFactory;
    }

    private Map<String, String> getClientDBType() throws IOException {
        InputStream inputStream = loadDBType().getInputStream();
        List<String> strings = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.toList());
        String output = strings.get(0);
        String[] splitedOutput = output.split("=");
        Map<String, String> clientDBType = new HashMap<>();
        clientDBType.put(splitedOutput[0], splitedOutput[1]);
        return clientDBType;
    }


    public Resource loadDBType() throws IOException {
       return resourceLoader.getResource("db.txt");
    }

}
