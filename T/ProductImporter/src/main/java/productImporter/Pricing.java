package productImporter;

import java.math.BigDecimal;

public class Pricing {
    private final BigDecimal listPrice;
    private final BigDecimal discount;

    public Pricing(BigDecimal listPrice, BigDecimal discount) {
        this.listPrice = listPrice;
        this.discount = discount;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
