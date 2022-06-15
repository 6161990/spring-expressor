package payment.domain;

public class Record {
    int amountSupplyPrice;
    int amountTaxPrice;
    int noExchangeablePointSupplyPoint;
    int noExchangeablePointTaxPoint;

    public Record(int supplyPrice, int taxPrice) {
        this.amountSupplyPrice = supplyPrice;
        this.amountTaxPrice = taxPrice;
    }

    public Record(int supplyPrice, int taxPrice, int noExchangeablePointSupplyPoint, int noExchangeablePointTaxPoint) {
        this.amountSupplyPrice = supplyPrice;
        this.amountTaxPrice = taxPrice;
        this.noExchangeablePointSupplyPoint = noExchangeablePointSupplyPoint;
        this.noExchangeablePointTaxPoint = noExchangeablePointTaxPoint;
    }

    public int get합계공급가액() {
        return amountSupplyPrice;
    }

    public int get합계세액() {
        return amountTaxPrice;
    }

    public int get현금화불가능포인트공급가액() {
        return noExchangeablePointSupplyPoint;
    }

    public int get현금화불가능포인트세액() {
        return noExchangeablePointTaxPoint;
    }
}