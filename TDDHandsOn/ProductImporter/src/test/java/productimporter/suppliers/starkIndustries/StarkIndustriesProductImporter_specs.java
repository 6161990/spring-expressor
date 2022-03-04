package productimporter.suppliers.starkIndustries;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.params.ParameterizedTest;
import productimporter.DomainArgumentsSource;
import productimporter.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    // starkIndustries 상품을 도메인에 맞게 잘 변형되는지 확인하는 테스트 : mockists의 테스트 관점 -> 값을 변환하는 translator에게 특정 조건을 그냥 setUp() 해주었고, 변환된 값의 결과에 대해서는 관심이없다.
    @ParameterizedTest
    @DomainArgumentsSource
    void sut_correctly_translates_source_products(StarkIndustriesProduct[] sourceProducts, Product[] product) {
        //Arrange
        var productSource = mock(StarkIndustriesProductSource.class);
        when(productSource.getAllProducts()).thenReturn(Arrays.asList(sourceProducts));

        var translator = mock(StarkIndustriesProductTranslator.class);

        List<Tuple> tuples = IntStream.range(0, Math.min(sourceProducts.length, product.length))  // intStream 의 배열의 인덱스를 나타내는데 sourceProducts와 product의 길이가 다를 수도 있으니, 작은 값으로 지정
                .mapToObj(index -> Tuple.tuple(sourceProducts[index], product[index])).collect(Collectors.toList()); //Tuple로 만들기 위해 mapToObj

        for(Tuple tuple : tuples){
            Object[] values = tuple.toArray();
            when(translator.translate((StarkIndustriesProduct) values[0])).thenReturn((Product) values[1]);
        }

        var sut = new StarkIndustriesProductImporter(productSource, translator);

        //Act
        Iterable<Product> actual = sut.fetchProducts();

        //Assert
        assertThat(actual).containsExactly(product);

    }
}
