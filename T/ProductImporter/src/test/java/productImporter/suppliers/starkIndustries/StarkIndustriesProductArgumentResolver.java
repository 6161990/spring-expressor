package productImporter.suppliers.starkIndustries;

import productImporter.DomainArgumentsResolver;

import java.util.Optional;
import java.util.UUID;

public class StarkIndustriesProductArgumentResolver implements DomainArgumentsResolver {
    @Override
    public Optional<Object> tryResolve(Class<?>[] parameterTypes) {
        if(parameterTypes.equals(StarkIndustriesProduct.class)) {
            return Optional.of(generate());
        } else if(parameterTypes.equals(StarkIndustriesProduct[].class)) {
            return Optional.of(new StarkIndustriesProduct[] { generate(), generate(), generate() });
        }
        return Optional.empty();
    }

    private static StarkIndustriesProduct generate() {
        String code = "code" + UUID.randomUUID().toString();
        String name = "name" + UUID.randomUUID().toString();
        int listPrice = random.nextInt(100000) + 100000;
        int discountAmount = random.nextInt(10000) + 10000;
        return new StarkIndustriesProduct(code, name, listPrice, discountAmount);
    }
}
