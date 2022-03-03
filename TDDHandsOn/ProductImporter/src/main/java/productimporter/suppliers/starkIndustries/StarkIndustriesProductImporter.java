package productimporter.suppliers.starkIndustries;

import productimporter.Product;
import productimporter.ProductImporter;

public class StarkIndustriesProductImporter implements ProductImporter {

    public StarkIndustriesProductImporter(StarkIndustriesProductSource productSource,
                                          StarkIndustriesProductTranslator translator) {
    }

    @Override
    public Iterable<Product> fetchProducts() {
        return null;
    }
}
