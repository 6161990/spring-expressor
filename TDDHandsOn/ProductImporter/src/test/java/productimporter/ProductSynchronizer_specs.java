package productimporter;

import org.junit.jupiter.params.ParameterizedTest;
import productimporter.suppliers.wayneenterprises.WayneEnterprisesProduct;
import productimporter.suppliers.wayneenterprises.WayneEnterprisesProductImporter;
import productimporter.suppliers.wayneenterprises.WayneEnterprisesProductSourceStub;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.mockito.Mockito.*;

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

    // 올바르지 않은 상품은 저장하지 않음을 확인하는 테스트 - Spy
    @ParameterizedTest
    @DomainArgumentsSource
    void sut_does_not_save_invalid_product(WayneEnterprisesProduct product) {
        var lowerBound = new BigDecimal(product.getListPrice() + 10000);// 더 비싼 가격으로 하한가 설정
        var validator = new ListPriceFilter(lowerBound);

        var stub = new WayneEnterprisesProductSourceStub(product);
        var importer = new WayneEnterprisesProductImporter(stub);
        var spy = new ProductInventorySpy();
        var sut = new ProductSynchronizer(importer, validator, spy);

        sut.run();

        assertThat(spy.getLog()).isEmpty();
    }

    // 올바르지 않은 상품은 저장하지 않음을 확인하는 테스트 - Mock
    @ParameterizedTest
    @DomainArgumentsSource
    void sut_really_does_not_save_invalid_product() {
        // Arrange
        var pricing = new Pricing(BigDecimal.TEN, BigDecimal.ZERO);
        var product = new Product("supplierName", "productCode", "productName", pricing);

        ProductImporter importer = mock(ProductImporter.class);
        when(importer.fetchProducts()).thenReturn(Arrays.asList(product));

        ProductValidator validator = mock(ProductValidator.class);
        when(validator.isValid(product)).thenReturn(false);

        ProductInventory inventory = mock(ProductInventory.class);

        var sut = new ProductSynchronizer(importer, validator, inventory);

        // Act
        sut.run();

        // Assert
        verify(inventory, never()).upsertProduct(product); // 해당 상품이 invalid 이므로, upsertProduct를 한번도 호출하지 않았는지를 never() 로 확인
    }


}
