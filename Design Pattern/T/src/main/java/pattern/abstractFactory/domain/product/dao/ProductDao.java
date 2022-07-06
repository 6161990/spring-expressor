package pattern.abstractFactory.domain.product.dao;

import pattern.abstractFactory.domain.product.Product;

public interface ProductDao {
    void insertProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Product product);
}
