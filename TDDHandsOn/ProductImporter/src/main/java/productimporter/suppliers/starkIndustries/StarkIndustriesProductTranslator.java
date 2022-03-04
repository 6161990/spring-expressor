package productimporter.suppliers.starkIndustries;

import productimporter.Pricing;
import productimporter.Product;

import java.math.BigDecimal;

public class StarkIndustriesProductTranslator {

    public Product translate(StarkIndustriesProduct source) {
        String supplierName = "STARK";
        String productCode = source.getCode();
        String productName = source.getName();
        Pricing pricing = getPricing(source);
        return new Product(supplierName, productCode, productName, pricing);
    }

    private Pricing getPricing(StarkIndustriesProduct source) {
        var listPrice = new BigDecimal(source.getListPrice());
        var discount = new BigDecimal(source.getDiscountAmount());
        return new Pricing(listPrice, discount);
    }

}
