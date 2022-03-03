package productimporter.suppliers.starkIndustries;

import org.junit.jupiter.params.ParameterizedTest;
import productimporter.DomainArgumentsSource;
import productimporter.Product;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StarkIndustriesProductImporter_specs {

    @ParameterizedTest
    @DomainArgumentsSource
    void sut_projects_all_products(StarkIndustriesProduct[] sourceProducts){ // 모든 상품 개수를 확인하는 테스트
        //Arrange
        var productSource = mock(StarkIndustriesProductSource.class);
        when(productSource.getAllProducts()).thenReturn(Arrays.asList(sourceProducts));

        var translator = mock(StarkIndustriesProductTranslator.class); //translator의 setUp은 필요없음. 여기서부터 실제 구현을 고려하게됨. setUp이 어디까진 필요한가!!
        var sut = new StarkIndustriesProductImporter(productSource, translator);

        //Act
        Iterable<Product> actual = sut.fetchProducts();

        //Assert
        assertThat(actual).hasSize(sourceProducts.length);
    }
}
