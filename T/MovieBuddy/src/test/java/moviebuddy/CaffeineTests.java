package moviebuddy;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class CaffeineTests {

    @Test
    void useCache() throws InterruptedException {
        Cache<String, Object> cache = Caffeine.newBuilder()
                .expireAfterWrite(200, TimeUnit.MILLISECONDS)
                .maximumSize(100)
                .build();

        String key = "springrunner";
        Object value = new Object();

        Assertions.assertNull(cache.getIfPresent(key));

        cache.put(key, value);
        Assertions.assertEquals(value, cache.getIfPresent(key));

        TimeUnit.MILLISECONDS.sleep(100);
        Assertions.assertEquals(value, cache.getIfPresent(key));

        TimeUnit.MILLISECONDS.sleep(100);
        Assertions.assertNull(cache.getIfPresent(key));
    }
}
