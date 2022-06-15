package payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import payment.domain.*;

import java.util.List;

public class PayTest {
    int SUPPLY_PRICE = 2345;
    int TAX_PRICE = 235;

    @Test
    void 현금화_불가능_포인트의_공급가액() {
        List<Payment> paymentList = List.of(Payment.of("noExchangeablePoint", SUPPLY_PRICE + TAX_PRICE));
        List<Product> productList = List.of();
        Command command = new Command(paymentList, productList);
        RecodeMaker recodeMaker = new RecodeMaker();

        Record actual = recodeMaker.createCommunityRecode(command);

        Assertions.assertThat(SUPPLY_PRICE).isEqualTo(actual.get현금화불가능포인트공급가액());
    }

    @Test
    void 현금화_불가능_포인트의_세액() {
        List<Payment> paymentList = List.of(Payment.of("noExchangeablePoint", SUPPLY_PRICE + TAX_PRICE));
        List<Product> productList = List.of();
        Command command = new Command(paymentList, productList);
        RecodeMaker recodeMaker = new RecodeMaker();

        Record actual = recodeMaker.createCommunityRecode(command);

        Assertions.assertThat(TAX_PRICE).isEqualTo(actual.get현금화불가능포인트세액());
    }

}
