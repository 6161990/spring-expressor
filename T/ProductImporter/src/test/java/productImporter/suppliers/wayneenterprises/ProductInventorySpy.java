package productImporter.suppliers.wayneenterprises;

import productImporter.Product;
import productImporter.ProductInventory;

import java.util.ArrayList;

public class ProductInventorySpy implements ProductInventory {

    private final ArrayList<Product> log;

    public ProductInventorySpy() {
        this.log = new ArrayList<>();
    }

    public ArrayList<Product> getLog() {
        return log;
    }

    @Override
    public void upsertProduct(Product product) {
        log.add(product);
    }
}
