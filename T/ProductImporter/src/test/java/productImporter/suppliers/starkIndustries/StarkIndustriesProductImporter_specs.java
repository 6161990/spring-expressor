package productImporter.suppliers.starkIndustries;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import productImporter.DomainArgumentsSource;
import productImporter.Product;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StarkIndustriesProductImporter_specs {

    @DisplayName("StarkIndustries 사가 넘겨준 상품 수 만큼 커머스 서비스에 등록된다")
    @ParameterizedTest
    @DomainArgumentsSource
    void sut_projects_all_product(StarkIndustriesProduct[] sourceProducts){
        // Arrange
        var productSource = mock(StarkIndustriesProductSource.class);
        when(productSource.getAllProducts()).thenReturn(Arrays.asList(sourceProducts));
        var translator = mock(StarkIndustriesProductTranslator.class);
        var sut = new StarkIndustriesProductImporter(productSource, translator);

        // Act
        Iterable<Product> actual = sut.fetchProducts();

        // Assert
        assertThat(actual).hasSize(sourceProducts.length);
    }

    /**
    * [Step7. StarkIndustries 사가 넘겨준 상품 수 만큼 커머스 서비스에 등록된다]
    */
}
