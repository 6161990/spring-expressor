package productimporter;

import java.math.BigDecimal;

public final class Pricing {
    private final BigDecimal listPrice; // 정가
    private final BigDecimal discount;  // 할인가

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
