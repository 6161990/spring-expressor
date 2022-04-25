package productImporter;

import java.util.stream.StreamSupport;

public class ProductSynchronizer {

    private final ProductImporter importer;
    private final ProductValidator validator;
    private final ProductInventory inventory;

    public ProductSynchronizer(ProductImporter importer, ProductValidator validator, ProductInventory inventory) {
        this.importer = importer;
        this.validator = validator;
        this.inventory = inventory;
    }

    public void run() {
        StreamSupport.stream(importer.fetchProducts().spliterator(), false).filter(validator::isValid).forEach(inventory::upsertProduct);
       /*
       for(Product product : importer.fetchProducts()){
           if(validator.isValid(product)){ // Step5. 올바르지 않은 상품은 저장하지 않는다.
               inventory.upsertProduct(product); // Step4. ProductSynchronizer 가 ProductInventory 에 상품을 잘 저장한다.
           }
       }*/
    }
}
