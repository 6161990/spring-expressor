package pattern.builder.payloadVersion;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class PaymentMethod {

    String paymentType;
    long amount;

    public static PaymentMethod of(String paymentType, long amount) {
        return new PaymentMethod(paymentType, amount);
    }

    public PaymentMethod(String paymentType, long amount) {
        this.paymentType = paymentType;
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public long getAmount() {
        return amount;
    }
}
