package productImporter.suppliers.starkIndustries;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import productImporter.DomainArgumentsSource;
import productImporter.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StarkIndustriesProductImporter_specs {

    @DisplayName("StarkIndustries 사가 넘겨준 상품 수 만큼 커머스 서비스에 등록된다")
    @ParameterizedTest
    @DomainArgumentsSource
    void sut_projects_all_products(StarkIndustriesProduct[] sourceProducts) {
        var productSource = mock(StarkIndustriesProductSource.class);
        when(productSource.getAllProducts()).thenReturn(Arrays.asList(sourceProducts));

        var translator = mock(StarkIndustriesProductTranslator.class);
        var sut = new StarkIndustriesProductImporter(productSource, translator);

        Iterable<Product> actual = sut.fetchProducts();

        assertThat(actual).hasSize(sourceProducts.length);
    }

    @DisplayName("StarkIndustries 사가 넘겨준 상품을 우리 커머스에 맞게 잘 변환한다")
    @ParameterizedTest
    @DomainArgumentsSource
    void sut_correctly_translates_source_products(StarkIndustriesProduct[] sourceProducts, Product[] products) {
        var productSource = mock(StarkIndustriesProductSource.class);
        when(productSource.getAllProducts()).thenReturn(Arrays.asList(sourceProducts));

        var translator = mock(StarkIndustriesProductTranslator.class);

        List<Tuple> tuples = IntStream.range(0, Math.min(sourceProducts.length, products.length))
                .mapToObj(index -> Tuple.tuple(sourceProducts[index], products[index])).collect(Collectors.toList());

        for(Tuple tuple: tuples){
            Object[] values = tuple.toArray();
            when(translator.translate((StarkIndustriesProduct) values[0])).thenReturn((Product) values[1]);
        }

        var sut = new StarkIndustriesProductImporter(productSource, translator);

        Iterable<Product> actual = sut.fetchProducts();

        assertThat(actual).containsExactly(products);
    }
}
