package productImporter;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public class ProductArgumentResolver implements DomainArgumentResolver{

    @Override
    public Optional<Object> tryResolve(Class<?> parameterType) {
        return Optional.empty();
    }
}
