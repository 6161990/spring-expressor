package productImporter.suppliers.starkIndustries;

import productImporter.Product;
import productImporter.ProductImporter;

import java.util.ArrayList;

public class StarkIndustriesProductImporter implements ProductImporter {
    private final StarkIndustriesProductSource productSource;
    private final StarkIndustriesProductTranslator translator;

    public StarkIndustriesProductImporter(StarkIndustriesProductSource productSource, StarkIndustriesProductTranslator translator) {
        this.productSource = productSource;
        this.translator = translator;
    }


    @Override
    public Iterable<Product> fetchProducts() {
        var products = new ArrayList<Product>();
        for(StarkIndustriesProduct product: productSource.getAllProducts()){
            products.add(translator.translate(product));
        }
        return products;
    }
}
