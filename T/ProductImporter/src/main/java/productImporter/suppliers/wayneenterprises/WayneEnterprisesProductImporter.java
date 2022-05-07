package productImporter.suppliers.wayneenterprises;

import productImporter.Product;
import productImporter.ProductImporter;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class WayneEnterprisesProductImporter implements ProductImporter {
    private final WayneEnterprisesProductSource dataSource;
    private final WayneEnterprisesProductTranslator translator;

    public WayneEnterprisesProductImporter(WayneEnterprisesProductSource dataSource) {
        this.dataSource = dataSource;
        translator = new WayneEnterprisesProductTranslator();
    }

    @Override
    public Iterable<Product> fetchProducts() {
        return StreamSupport.stream(dataSource.fetchProducts().spliterator(), false)
                .map(translator::translate).collect(Collectors.toList());
    }
}
