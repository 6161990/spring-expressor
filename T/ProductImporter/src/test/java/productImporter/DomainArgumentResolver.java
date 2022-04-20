package productImporter;

import productImporter.suppliers.starkIndustries.StarkIndustriesProductArgumentResolver;
import productImporter.suppliers.wayneenterprises.WayneEnterprisesProductArgumentResolver;

import java.util.Optional;
import java.util.Random;

/** 컴포짓 패턴 이용 */
public interface DomainArgumentResolver {
    Optional<Object> tryResolve(Class<?> parameterType);

    static Random random = new Random();

    static DomainArgumentResolver instance = new CompositeArgumentResolver(new ProductArgumentResolver(), new StarkIndustriesProductArgumentResolver(), new WayneEnterprisesProductArgumentResolver());
}
