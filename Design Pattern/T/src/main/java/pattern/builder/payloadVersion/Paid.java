package pattern.builder.payloadVersion;

import java.time.LocalDateTime;

public interface Paid {
    MerchantItems[] getMerchantItems();

    PaymentMethod[] getPaymentMethods();

    boolean onlyOnePaidMethod();

    LocalDateTime getOccurredAt();

    default Allocated[] allocate() {
        PaymentMethod[] paymentMethods = this.getPaymentMethods();
        MerchantItems[] merchantItems = this.getMerchantItems();
        Allocated[] allocateds = new Allocated[paymentMethods.length * merchantItems.length];
        int i = 0;
        if (this.onlyOnePaidMethod()) {
            for (PaymentMethod each : paymentMethods) {
                for (MerchantItems item : merchantItems) {
                    allocateds[i++] = new Allocated(PaymentMethod.of(each.getPaymentType(), item.getPrice()), item.getPurchasedType(), 0);
                }
            }
            return allocateds;
        }
        return new Allocated[]{};
    }
}
