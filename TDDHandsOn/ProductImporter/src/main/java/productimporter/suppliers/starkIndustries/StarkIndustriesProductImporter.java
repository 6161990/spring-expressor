package productimporter.suppliers.starkIndustries;

import productimporter.Product;
import productimporter.ProductImporter;

import java.util.ArrayList;

public class StarkIndustriesProductImporter implements ProductImporter {

    private final StarkIndustriesProductSource productSource;

    public StarkIndustriesProductImporter(StarkIndustriesProductSource productSource,
                                          StarkIndustriesProductTranslator translator) {
        this.productSource = productSource;
    }

    @Override
    public Iterable<Product> fetchProducts() {
        Iterable<StarkIndustriesProduct> source = productSource.getAllProducts();
        var products = new ArrayList<Product>();
        for (StarkIndustriesProduct s : source) {
            products.add(null);
        }
        return products;
    }
}
