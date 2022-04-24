package productImporter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import productImporter.*;
import productImporter.suppliers.wayneenterprises.ProductInventorySpy;
import productImporter.suppliers.wayneenterprises.WayneEnterprisesProduct;
import productImporter.suppliers.wayneenterprises.WayneEnterprisesProductImporter;
import productImporter.suppliers.wayneenterprises.WayneEnterprisesProductSourceStub;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductSynchronizer_specs {

    @DisplayName("ProductSynchronizer 가 ProductInventory에 상품을 잘 저장한")
    @ParameterizedTest
    @DomainArgumentsSource
    void sut_correctly_saves_products(WayneEnterprisesProduct[] products){
        var stub = new WayneEnterprisesProductSourceStub(products);
        var importer = new WayneEnterprisesProductImporter(stub);
        var validator = new ListPriceFilter(BigDecimal.ZERO); // 특정 값 이상만 유효한 것으로 판단하는 필터(0이상의 값의 상품을 통과시킴)
        var spy = new ProductInventorySpy();
        var sut = new ProductSynchronizer(importer, validator, spy);

        sut.run();

        Iterable<Product> expected = importer.fetchProducts();
        assertThat(spy.getLog()).usingRecursiveFieldByFieldElementComparator().containsAll(expected); // importer 가 반환하는 모든 상품들이 inventory에 그대로 출력되는지 확인
    }

    /**
     * [Step4. ProductSynchronizer 가 ProductInventory 에 상품을 잘 저장한다.]
     * */
}
