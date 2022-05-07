package productImporter.suppliers.wayneenterprises;

import java.util.Arrays;

/** 입력받은 값을 그대로 갖고 있다가 호출될 때 그대로 반환 -> 준비된 값을 가지고 있는 셈*/
public class WayneEnterprisesProductSourceStub implements WayneEnterprisesProductSource{

    private final WayneEnterprisesProduct[] products;

    public WayneEnterprisesProductSourceStub(WayneEnterprisesProduct... products) {
        this.products = products;
    }

    @Override
    public Iterable<WayneEnterprisesProduct> fetchProducts() {
        return Arrays.asList(products);
    }
}
