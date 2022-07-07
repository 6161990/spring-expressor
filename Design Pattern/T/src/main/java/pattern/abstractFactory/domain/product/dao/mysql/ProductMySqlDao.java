package pattern.abstractFactory.domain.product.dao.mysql;

import pattern.abstractFactory.domain.product.Product;
import pattern.abstractFactory.domain.product.dao.ProductDao;

public class ProductMySqlDao implements ProductDao {
    @Override
    public String insertProduct(Product product) {
        return "insert into MYSQL DB productId =" + product.getProductId();
    }

    @Override
    public String updateProduct(Product product) {
        return "update into MYSQL DB productId =" + product.getProductId();
    }

    @Override
    public String deleteProduct(Product product) {
        return "delete into MYSQL DB productId =" + product.getProductId();
    }
}
