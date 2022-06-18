package moviebuddy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PropertiesTests {

    @Test
    void Load_PropertiesFile() throws IOException {
        Properties config = new Properties();
        config.load(Files.newInputStream(Path.of("./src/test/resources/config.properties")));

        Assertions.assertEquals(1, config.size());
        Assertions.assertEquals("arawn", config.get("name"));
    }
}
