package productImporter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import productImporter.suppliers.wayneenterprises.WayneEnterprisesProduct;
import productImporter.suppliers.wayneenterprises.WayneEnterprisesProductImporter;
import productImporter.suppliers.wayneenterprises.WayneEnterprisesProductSourceStub;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProductSynchronizer_specs {

    @DisplayName("ProductSynchronizer 가 ProductInventory 에 상품을 잘 저장한다.")
    @ParameterizedTest
    @DomainArgumentsSource
    void sut_correctly_saves_products(WayneEnterprisesProduct[] products) {
        var stub = new WayneEnterprisesProductSourceStub(products);
        var importer = new WayneEnterprisesProductImporter(stub);
        var validator = new ListPriceFilter(BigDecimal.ZERO);
        var spy = new ProductInventorySpy();
        var sut = new ProductSynchronizer(importer, validator, spy);

        sut.run();

        Iterable<Product> expected = importer.fetchProducts();
        assertThat(spy.getLog()).usingRecursiveFieldByFieldElementComparator().containsAll(expected);


        /**
         * usingRecursiveFieldByFieldElementComparator : Collection 에서 자식까지 모든 필드 비교
         * */
    }

    @DisplayName("올바르지 않은 상품은 저장하지 않는다.")
    @ParameterizedTest
    @DomainArgumentsSource
    void sut_does_not_save_invalid_product(WayneEnterprisesProduct product) {
        var lowerBound = new BigDecimal(product.getListPrice() + 10000);
        var validator = new ListPriceFilter(lowerBound);

        var stub = new WayneEnterprisesProductSourceStub(product);
        var importer = new WayneEnterprisesProductImporter(stub);
        var spy = new ProductInventorySpy();
        var sut = new ProductSynchronizer(importer, validator, spy);

        sut.run();

        assertThat(spy.getLog()).isEmpty();
    }


    @DisplayName("Spy 를 사용하던 ProductSynchronizer 를 Mock 을 사용한다.")
    @ParameterizedTest
    @DomainArgumentsSource
    void sut_really_does_not_save_invalid_product() {
        var pricing = new Pricing(BigDecimal.TEN, BigDecimal.ZERO);
        var product = new Product("supplierName", "productCode", "productName", pricing);

        ProductImporter importer = mock(ProductImporter.class);
        when(importer.fetchProducts()).thenReturn(Arrays.asList(product));

        var validator = mock(ProductValidator.class);
        when(validator.isValid(product)).thenReturn(false);

        ProductInventory inventory = mock(ProductInventory.class);

        var sut = new ProductSynchronizer(importer, validator, inventory);

        sut.run();

        verify(inventory, never()).upsertProduct(product);
    }
}

