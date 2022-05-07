package productImporter.suppliers.wayneenterprises;

import productImporter.Pricing;
import productImporter.Product;

import java.math.BigDecimal;

public class WayneEnterprisesProductTranslator {
    public Product translate(WayneEnterprisesProduct product) {
        Pricing pricing = getPricing(product);
        return new Product("WAYNE", product.getId(), product.getTitle(), pricing);
    }

    private Pricing getPricing(WayneEnterprisesProduct product) {
        int listPrice = product.getListPrice();
        int sellingPrice = product.getSellingPrice();
        var discount = new BigDecimal(listPrice - sellingPrice);
        return new Pricing(new BigDecimal(listPrice), discount);
    }
}
