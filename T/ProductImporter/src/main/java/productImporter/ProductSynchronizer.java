package productImporter;

import java.util.stream.StreamSupport;

public class ProductSynchronizer {
    private final ProductImporter productImporter;
    private final ProductValidator productValidator;
    private final ProductInventory productInventory;

    public ProductSynchronizer(ProductImporter productImporter, ProductValidator productValidator, ProductInventory productInventory) {
        this.productImporter = productImporter;
        this.productValidator = productValidator;
        this.productInventory = productInventory;
    }

    public void run(){
        // TODO STREAM 으로 변경
        for(Product product : productImporter.fetchProducts()){
            if(productValidator.isValid(product)){
                productInventory.upsertProduct(product);
            }
        }
    }
}
