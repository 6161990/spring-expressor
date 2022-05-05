package productImporter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import productImporter.suppliers.wayneenterprises.WayneEnterprisesProductSourceStub;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProductSynchronizer_specs {

    @DisplayName("ProductSynchronizer 가 ProductInventory 에 상품을 잘 저장한다.")
    @ParameterizedTest
    void sut_correctly_saves_products() {


        /**
         * usingRecursiveFieldByFieldElementComparator : Collection 에서 자식까지 모든 필드 비교
         * */
    }

    @DisplayName("올바르지 않은 상품은 저장하지 않는다.")
    @ParameterizedTest
    void sut_does_not_save_invalid_product( ) {

    }


    @DisplayName("Spy 를 사용하던 ProductSynchronizer 를 Mock 을 사용한다.")
    @ParameterizedTest
    void sut_really_does_not_save_invalid_product() {
    }
}

