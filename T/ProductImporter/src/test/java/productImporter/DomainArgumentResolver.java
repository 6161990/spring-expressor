package productImporter;

import java.util.Optional;
import java.util.Random;

public interface DomainArgumentResolver {

    Optional<Object> tryResolve(Class<?> parameterType);

    Random random = new Random();

    DomainArgumentResolver instance = new ProductArgumentResolver();
}
