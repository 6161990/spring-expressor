package pattern.abstractFactory.domain.product.dao.oracle;

import pattern.abstractFactory.domain.product.Product;
import pattern.abstractFactory.domain.product.dao.ProductDao;

public class ProductOracleDao implements ProductDao {

    @Override
    public String insertProduct(Product product) {
        return "insert into Oracle DB productId =" + product.getProductId();
    }

    @Override
    public String updateProduct(Product product) {
        return "update into Oracle DB productId =" + product.getProductId();
    }

    @Override
    public String deleteProduct(Product product) {
        return "delete into Oracle DB productId =" + product.getProductId();
    }
}
