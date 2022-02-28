package productimporter.suppliers.wayneenterprises;

import java.util.Arrays;

public class WayneEnterprisesProductSourceStub implements WayneEnterprisesProductSource {

    private WayneEnterprisesProduct[] products;

    public WayneEnterprisesProductSourceStub(WayneEnterprisesProduct... products) {
        this.products = products; // Stub 생성자에서 입력받은 products 가 준비된 답이 되는 셈.
    }

    @Override
    public Iterable<WayneEnterprisesProduct> fetchProducts() {
        return Arrays.asList(products);
    }
}
