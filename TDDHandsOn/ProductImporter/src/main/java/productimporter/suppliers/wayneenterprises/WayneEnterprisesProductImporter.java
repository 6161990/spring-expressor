package productimporter.suppliers.wayneenterprises;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import productimporter.Product;
import productimporter.ProductImporter;

public final class WayneEnterprisesProductImporter implements ProductImporter {

    private final WayneEnterprisesProductSource dataSource;

    public WayneEnterprisesProductImporter(WayneEnterprisesProductSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Iterable<Product> fetchProducts() {
        return StreamSupport.stream(dataSource.fetchProducts().spliterator(), false)
                .map(x -> new Product("WAYNE", x.getId(), null, null)).collect(Collectors.toList());
    }

}
