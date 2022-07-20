package pattern.adapter.payloadVersion;

import pattern.builder.payloadVersion.Paid;
import pattern.builder.payloadVersion.MerchantItems;
import pattern.builder.payloadVersion.PaymentMethod;

import java.time.LocalDateTime;

public class FirstPaid implements Paid {

    private PaidPayloadVersion1 paidPayloadVersion1;

    @Override
    public MerchantItems[] getMerchantItems() {
        return new MerchantItems[0];
    }

    @Override
    public PaymentMethod[] getPaymentMethods() {
        return new PaymentMethod[0];
    }

    @Override
    public boolean onlyOnePaidMethod() {
        return false;
    }

    @Override
    public LocalDateTime getOccurredAt() {
        return null;
    }
}
