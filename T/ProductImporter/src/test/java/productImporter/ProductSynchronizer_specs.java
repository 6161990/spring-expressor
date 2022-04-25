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

    @DisplayName("올바르지 않은 상품은 저장하지 않는다.")
    @ParameterizedTest
    @DomainArgumentsSource
    void sut_does_not_save_invalid_product(WayneEnterprisesProduct product){
        // Arrange
        var lowerBound = new BigDecimal(product.getListPrice() + 10000); // 더 비싼 가격으로 하한가 설정
        var validator = new ListPriceFilter(lowerBound);

        var stub = new WayneEnterprisesProductSourceStub(product);
        var importer = new WayneEnterprisesProductImporter(stub);
        var spy = new ProductInventorySpy();
        var sut = new ProductSynchronizer(importer, validator, spy);

        // Act
        sut.run();

        // Assert
        assertThat(spy.getLog()).isEmpty();
    }


    /**
     * [Step4. ProductSynchronizer 가 ProductInventory 에 상품을 잘 저장한다.]
     * [Step5. 올바르지 않은 상품은 저장하지 않는다.]
     * */
}
