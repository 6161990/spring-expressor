package moviebuddy.cache;

import java.util.Objects;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

public class CachingAdvice implements MethodInterceptor {
	
	private final CacheManager cacheManager;
	
	public CachingAdvice(CacheManager cacheManager) {
		this.cacheManager = Objects.requireNonNull(cacheManager);
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// 캐시된 데이터 존재하면, 즉시 반환 처리
		Cache cache = cacheManager.getCache(invocation.getThis().getClass().getName());
		Object cachedValue = cache.get(invocation.getMethod().getName(), Object.class);
		
		if(Objects.nonNull(cachedValue)) {
			return cachedValue;
		}
		
		// 캐시된 데이터 없으면, 대상 객체에 명령을 위임하고, 반환된 값을 캐시에 저장 후 반환처리
		cachedValue = invocation.proceed();
		cache.put(invocation.getMethod().getName(), cachedValue);
		
		return cachedValue;
	}

}
