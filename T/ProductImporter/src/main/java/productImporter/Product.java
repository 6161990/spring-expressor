package productImporter;

import lombok.Getter;

@Getter
public class Product {
    private final String supplierName;
    private final String productCode;
    private final String productName;
    private final Pricing pricing;

    public Product(String supplierName, String productCode, String productName, Pricing pricing) {
        this.supplierName = supplierName;
        this.productCode = productCode;
        this.productName = productName;
        this.pricing = pricing;
    }
}
