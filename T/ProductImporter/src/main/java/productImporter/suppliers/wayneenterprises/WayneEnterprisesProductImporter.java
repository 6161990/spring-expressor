package productImporter.suppliers.wayneenterprises;

import productImporter.Product;
import productImporter.ProductImporter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public final class WayneEnterprisesProductImporter implements ProductImporter {

    private final WayneEnterprisesProductSource dataSource;
    private final WayneEnterprisesProductTranslator translator;

    public WayneEnterprisesProductImporter(WayneEnterprisesProductSource dataSource) {
        this.dataSource = dataSource;
        this.translator = new WayneEnterprisesProductTranslator();
    }

    @Override
    public Iterable<Product> fetchProducts() {
        /*var products = new ArrayList<WayneEnterprisesProduct>();
        dataSource.fetchProducts().forEach(products::add);
        return Arrays.asList(new Product[products.size()]);*/
        return StreamSupport.stream(dataSource.fetchProducts().spliterator(), false)
                .map(x -> new Product("WAYNE", null, null, null)).collect(Collectors.toList());
    }
}
