package pattern.abstractFactory.domain.product.dao;

import pattern.abstractFactory.domain.product.Product;

public interface ProductDao {
    String insertProduct(Product product);
    String updateProduct(Product product);
    String deleteProduct(Product product);
}
