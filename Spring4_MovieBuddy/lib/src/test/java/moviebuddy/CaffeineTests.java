package moviebuddy;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

public class CaffeineTests {

	@Test
	void useCache() {
		Cache<String, Object> cache = Caffeine.newBuilder()
				.build();
		
		String key = "carol";
		Object value = new Object();
		
		Assertions.assertNull(cache.getIfPresent(key));
	}
	
	@Test
	void useCache_putCache() {
		Cache<String, Object> cache = Caffeine.newBuilder()
				.build();
		
		String key = "carol";
		Object value = new Object();
		
		cache.put(key, value);
		Assertions.assertEquals(value, cache.getIfPresent(key));
	}
	
	@Test
	void useCache_managing() throws InterruptedException {
		Cache<String, Object> cache = Caffeine.newBuilder()
				.expireAfterWrite(200, TimeUnit.MILLISECONDS)
				.maximumSize(100)
				.build();
		
		String key = "carol";
		Object value = new Object();
		
		cache.put(key, value);
		Assertions.assertEquals(value, cache.getIfPresent(key));
		
		TimeUnit.MILLISECONDS.sleep(100);
		Assertions.assertEquals(value, cache.getIfPresent(key));
		
		TimeUnit.MILLISECONDS.sleep(100);
		Assertions.assertNull(cache.getIfPresent(key));

	}
}
