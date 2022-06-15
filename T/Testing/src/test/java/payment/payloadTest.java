package payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import payment.domain.*;

import java.util.List;

public class payloadTest {

  /*  @Test
    void 결제이벤트() {
        Calculator calculator = new Calculator(80000, 2580);
        RecodeMaker recodeMaker = new RecodeMaker();
        Record actual = recodeMaker.createCommunityRecode(calculator);
        Assertions.assertThat(72728).isEqualTo(actual.get합계공급가액());
    }

    @Test
    void 결제이벤트_합세액() {
        Calculator calculator = new Calculator(80000, 2580);
        RecodeMaker recodeMaker = new RecodeMaker();*//**//*
        Record actual = recodeMaker.createCommunityRecode(calculator);
        Assertions.assertThat(7272).isEqualTo(actual.get합계세액());
    }

    @Test
    void 결제이벤트_포인트_공급가액() {
        Calculator calculator = new Calculator(80000, 2580);
        RecodeMaker recodeMaker = new RecodeMaker();
        Record actual = recodeMaker.createCommunityRecode(calculator);
        Assertions.assertThat(2346).isEqualTo(actual.get현금화불가능포인트공급가액());
    }

    @Test
    void 결제이벤트_포인트_세액() {
        Calculator calculator = new Calculator(80000, 2580);
        RecodeMaker recodeMaker = new RecodeMaker();
        Record actual = recodeMaker.createCommunityRecode(calculator);
        Assertions.assertThat(234).isEqualTo(actual.get현금화불가능포인트세액());
    }*/

    @Test
    void name() {
        // TODO : Builder 패턴 사용해서 만들기
        Payment p1 = Payment.freePoint(10000);
        Payment p2 = Payment.payPoint(20000);
        Payment card = Payment.card(230000);
        Product product1 = Product.of("bookmeeting1",  57500);
        Product product2 = Product.of("bookmeeting2",  57500);
        Product product3 = Product.of("bookmeeting3",  57500);
        Product product4 = Product.of("bookmeeting4",  57500);
        Product product5 = Product.of("community",  80000);
        List<Payment> paymentList = List.of(
                p1,
                p2,
                card
        );
        List<Product> productList = List.of(
                product1,
                product2,
                product3,
                product4,
                product5
        );
        Command command = new Command(paymentList, productList);
        RecodeMaker recodeMaker = new RecodeMaker();
    }

}
