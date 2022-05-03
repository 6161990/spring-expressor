package productImporter;


import productImporter.suppliers.starkIndustries.StarkIndustriesProductArgumentResolver;
import productImporter.suppliers.wayneenterprises.WayneEnterprisesProductArgumentResolver;

import java.util.Optional;
import java.util.Random;

public interface DomainArgumentsResolver {

    Optional<Object> tryResolve(Class<?> parameterTypes);

    static Random random = new Random();

    static DomainArgumentsResolver instance = new CompositeArgumentResolver(
            new ProductArgumentResolver(),
            new StarkIndustriesProductArgumentResolver(),
            new WayneEnterprisesProductArgumentResolver());

}
