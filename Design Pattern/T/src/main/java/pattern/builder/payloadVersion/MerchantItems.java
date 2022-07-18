package pattern.builder.payloadVersion;

public class MerchantItems {
    String purchasedType;
    long price;
    long remained;

    public MerchantItems(String purchasedType, long price, long remained) {
        this.purchasedType = purchasedType;
        this.price = price;
        this.remained = remained;
    }

    public String getPurchasedType() {
        return purchasedType;
    }

    public long getPrice() {
        return price;
    }

    public long getRemained() {
        return remained;
    }
}
