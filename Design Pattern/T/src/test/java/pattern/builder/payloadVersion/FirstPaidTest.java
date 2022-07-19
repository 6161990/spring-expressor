package pattern.builder.payloadVersion;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstPaidTest {
    @Test
    void FirstPaidBuilder_를_이용해_테스트합니다() {
        Paid paid = FirstPaidBuilder.builder()
                .with(PaymentMethod.of("CARD", 4000))
                .with(new MerchantItems("GOODS", 2000, 2000))
                .with(new MerchantItems("SHIP", 2000, 2000))
                .with(LocalDateTime.now())
                .build();

        Allocated[] allocate = paid.allocate();

        assertThat(allocate).containsExactly(allocate[0], allocate[1]);
    }

    private static class FirstPaidBuilder {

        private final List<MerchantItems> merchantItems = new ArrayList<>();
        private final List<PaymentMethod> paymentMethods = new ArrayList<>();
        private LocalDateTime occurredAt;

        public static FirstPaidBuilder builder() {
            return new FirstPaidBuilder();
        }

        public FirstPaidBuilder with(PaymentMethod card) {
            this.paymentMethods.add(card);
            return this;
        }

        public FirstPaidBuilder with(MerchantItems goods) {
            this.merchantItems.add(goods);
            return this;
        }

        public FirstPaidBuilder with(LocalDateTime now) {
            this.occurredAt = now;
            return this;
        }

        public Paid build() {
            return new Paid() {
                @Override
                public MerchantItems[] getMerchantItems() {
                    return merchantItems.toArray(new MerchantItems[0]);
                }

                @Override
                public PaymentMethod[] getPaymentMethods() {
                    return paymentMethods.toArray(new PaymentMethod[0]);
                }

                @Override
                public boolean onlyOnePaidMethod() {
                    return paymentMethods.size() == 1;
                }

                @Override
                public LocalDateTime getOccurredAt() {
                    return LocalDateTime.now();
                }
            };
        }

    }
}
