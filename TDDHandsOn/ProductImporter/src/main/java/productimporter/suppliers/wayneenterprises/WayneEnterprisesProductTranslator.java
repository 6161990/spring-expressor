package productimporter.suppliers.wayneenterprises;

import productimporter.Pricing;
import productimporter.Product;

import java.math.BigDecimal;

final class WayneEnterprisesProductTranslator {

    public Product translate(WayneEnterprisesProduct x) {
        return new Product("WAYNE", x.getId(), x.getTitle(),
                new Pricing(new BigDecimal(x.getListPrice()), new BigDecimal(x.getListPrice() - x.getSellingPrice())));
    }

}
