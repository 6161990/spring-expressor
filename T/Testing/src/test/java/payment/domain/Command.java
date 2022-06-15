package payment.domain;

import java.util.List;

public class Command { // TODO EventPayload 에서 List<Record> 를 생성하기 위해 필요한 재료 (결제수단+상품)
    List<Payment> paymentList;
    List<Product> productList;

    public Command(List<Payment> paymentList, List<Product> productList) {
        this.paymentList = paymentList;
        this.productList = productList;
    }

}
