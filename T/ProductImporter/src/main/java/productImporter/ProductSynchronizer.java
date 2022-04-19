package productImporter;

public class ProductSynchronizer {

    private final ProductImporter importer;
    private final ProductValidator validator;
    private final ProductInventory inventory;

    public ProductSynchronizer(ProductImporter importer, ProductValidator validator, ProductInventory inventory) {
        this.importer = importer;
        this.validator = validator;
        this.inventory = inventory;
    }

    //TODO: Stream 으로 구현 변경하기
    public void run() {
       for(Product product : importer.fetchProducts()){
           if(validator.isValid(product)){
               inventory.upsertProduct(product);
           }
       }
    }
}
