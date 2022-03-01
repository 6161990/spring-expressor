package productimporter.suppliers.wayneenterprises;

import productimporter.Pricing;
import productimporter.Product;

import java.math.BigDecimal;

final class WayneEnterprisesProductTranslator {

    public Product translate(WayneEnterprisesProduct x) {
        Pricing pricing = getPricing(x);
        return new Product("WAYNE", x.getId(), x.getTitle(),
                pricing);
    }

    private Pricing getPricing(WayneEnterprisesProduct x) {
        int listPrice = x.getListPrice();
        int sellingPrice = x.getSellingPrice();
        var discount = new BigDecimal(listPrice - sellingPrice);
        return new Pricing(new BigDecimal(listPrice), discount);
    }

}
