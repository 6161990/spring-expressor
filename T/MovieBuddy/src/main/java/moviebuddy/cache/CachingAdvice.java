package moviebuddy.cache;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Objects;

public class CachingAdvice implements MethodInterceptor {

    private final CacheManager cacheManager;

    public CachingAdvice(CacheManager cacheManager) {
        this.cacheManager = Objects.requireNonNull(cacheManager);
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Cache cache = cacheManager.getCache(invocation.getThis().getClass().getName()); // invocation.getThis().getClass() = CsvMovieReader or XmlMovieReader 가 동적으로 적용될 것임.
        Object cachedValue = cache.get(invocation.getMethod().getName(), Object.class);
        if(Objects.nonNull(cachedValue)) {
            return cachedValue;
        }

        cachedValue = invocation.proceed();
        cache.put(invocation.getMethod().getName(), cachedValue); // invocation.getMethod().getName() == loadMovies

        return cachedValue;
    }
}
