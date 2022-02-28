package productimporter.suppliers.wayneenterprises;

import java.util.ArrayList;
import java.util.Arrays;

import productimporter.Product;
import productimporter.ProductImporter;

public final class WayneEnterprisesProductImporter implements ProductImporter {

    private final WayneEnterprisesProductSource dataSource;

    public WayneEnterprisesProductImporter(WayneEnterprisesProductSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Iterable<Product> fetchProducts() {
        ArrayList<WayneEnterprisesProduct> products = new ArrayList<>();
        dataSource.fetchProducts().forEach(products::add);
        return Arrays.asList(new Product[products.size()]);
    }

}
