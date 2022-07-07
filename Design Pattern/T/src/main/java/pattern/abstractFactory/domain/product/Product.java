package pattern.abstractFactory.domain.product;

import lombok.Value;

@Value
public class Product {
    private String ProductId;
    private String ProductName;

    public Product(String productId, String productName) {
        ProductId = productId;
        ProductName = productName;
    }

    public String getProductId() {
        return ProductId;
    }

    public String getProductName() {
        return ProductName;
    }
}
