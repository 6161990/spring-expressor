package productimporter;

import org.junit.jupiter.params.ParameterizedTest;
import productimporter.suppliers.wayneenterprises.WayneEnterprisesProduct;
import productimporter.suppliers.wayneenterprises.WayneEnterprisesProductImporter;
import productimporter.suppliers.wayneenterprises.WayneEnterprisesProductSourceStub;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductSynchronizer_specs {

    // 상품들이 importer, validator를 거쳐 Inventory에 잘 저장이 되는지 확인하는 테스트 -> ProductSynchronizer 가 상품을 잘 저장한
    @ParameterizedTest
    @DomainArgumentsSource
    void sut_correctly_saves_products(WayneEnterprisesProduct[] products) {
        var stub = new WayneEnterprisesProductSourceStub(products); // DOC 1 을 위한 TEST DOUBLE Stub
        var importer = new WayneEnterprisesProductImporter(stub); // DOC 1
        var validator = new ListPriceFilter(BigDecimal.ZERO); // 특정 가격 이상만 유효한 상품으로 처리하는 Filter. 현재 0을 주고있으므로, 0원 이상인 상품은 모두 넘겨준다.
        var spy = new ProductInventorySpy(); // SUT 를 위한 TEST DOUBLE Spy
        var sut = new ProductSynchronizer(importer, validator, spy);

        sut.run();

        Iterable<Product> expected = importer.fetchProducts();
        assertThat(spy.getLog()).usingRecursiveFieldByFieldElementComparator().containsAll(expected);

        /**
         usingRecursiveFieldByFieldElementComparator() : Collection에서 자식까지 모든 필드 비교
         */
    }
}
