package productImporter.suppliers.wayneenterprises;

import productImporter.DomainArgumentsResolver;
import productImporter.suppliers.starkIndustries.StarkIndustriesProduct;

import java.util.Optional;
import java.util.UUID;

public class WayneEnterprisesProductArgumentResolver implements DomainArgumentsResolver {
    @Override
    public Optional<Object> tryResolve(Class<?> parameterTypes) {
        if(parameterTypes.equals(WayneEnterprisesProduct.class)){
            return Optional.of(generate());
        }else if(parameterTypes.equals(WayneEnterprisesProduct[].class)){
            return Optional.of(new WayneEnterprisesProduct[] { generate(), generate(), generate()});
        }
        return Optional.empty();
    }

    private static WayneEnterprisesProduct generate() {
        String id = "id" + UUID.randomUUID().toString();
        String title = "title" + UUID.randomUUID().toString();
        int listPrice = random.nextInt(100000) + 100000;
        int sellingPrice = listPrice - random.nextInt(10000) + 10000;
        return new WayneEnterprisesProduct(id, title, listPrice, sellingPrice);
    }
}
