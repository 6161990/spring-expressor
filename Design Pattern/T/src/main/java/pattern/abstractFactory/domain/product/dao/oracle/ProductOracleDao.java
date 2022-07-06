package pattern.abstractFactory.domain.product.dao.oracle;

import pattern.abstractFactory.domain.product.Product;
import pattern.abstractFactory.domain.product.dao.ProductDao;

public class ProductOracleDao implements ProductDao {

    @Override
    public void insertProduct(Product product) {
        System.out.println("insert into Oracle DB productId =" + product.getProductId());
    }

    @Override
    public void updateProduct(Product product) {
        System.out.println("update into Oracle DB productId =" + product.getProductId());
    }

    @Override
    public void deleteProduct(Product product) {
        System.out.println("delete into Oracle DB productId =" + product.getProductId());
    }
}
