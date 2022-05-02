package productImporter.suppliers.starkIndustries;

import productImporter.Product;

public interface StarkIndustriesProductSource {
    Iterable<StarkIndustriesProduct> getAllProducts();
}
