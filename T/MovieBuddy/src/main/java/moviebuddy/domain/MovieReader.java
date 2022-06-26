package moviebuddy.domain;

import javax.cache.annotation.CacheResult;
import java.util.List;

public interface MovieReader {

    @CacheResult(cacheName = "movies") // 상위에 적용해도 하위 클래스의 해당 메소드 캐싱 적용됨
    List<Movie> loadMovies();
}
