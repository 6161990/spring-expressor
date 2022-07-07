package pattern.abstractFactory;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import pattern.abstractFactory.factory.DaoFactory;
import pattern.abstractFactory.factory.MySqlDaoFactory;
import pattern.abstractFactory.factory.OracleDaoFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class LoadDaoFactory {

    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    public DaoFactory determineConcreteDaoFactory() throws Exception {
        DaoFactory daoFactory = null;

        InputStream inputStream = loadDBType().getInputStream();
        List<String> strings = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.toList());
        String output = strings.get(0);
        String[] splitedOutput = output.split("=");
        Map<String, String> map = new HashMap<>();
        map.put(splitedOutput[0], splitedOutput[1]);

        if(map.get("DBTYPE").equals("ORACLE")){
            daoFactory = new OracleDaoFactory();
        }else if(map.get("DBTYPE").equals("MYSQL")){
            daoFactory = new MySqlDaoFactory();
        }else {
            throw new Exception("Do Not Exist DB Type");
        }

        return daoFactory;
    }

    public Resource loadDBType() throws IOException {
       return resourceLoader.getResource("db.txt");
    }


}
