package productimporter;

import java.util.stream.StreamSupport;

public final class ProductSynchronizer {

    private final ProductImporter importer;
    private final ProductValidator validator;
    private final ProductInventory inventory;

    public ProductSynchronizer(ProductImporter importer, ProductValidator validator, ProductInventory inventory) {
        this.importer = importer;
        this.validator = validator;
        this.inventory = inventory;
    }

    public void run() {
        /**
         * Stream 으로 구현 변경
         * */
        StreamSupport.stream(importer.fetchProducts().spliterator(), false)
                .filter(validator::isValid)
                .forEach(inventory::upsertProduct);

/*        for (Product product : importer.fetchProducts()) {
            if(validator.isValid(product)){
                inventory.upsertProduct(product);
            }
        }*/

    }
}
