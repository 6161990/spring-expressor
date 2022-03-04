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

/**
 * WayneEnterprises 로 구현했던 sut_correctly_projects_source_properties 테스트 케이스와의 차이?
 *  1. WayneEnterprises 의 경우, WayneEnterprisesProductImporter 를 생성하고 나서 더이상의 구현이 필요없었음
 *  WayneEnterprisesProductImporter 구현 후, 리팩터링 하는 과정에서 WayneEnterprisesProductTranslator 를 추출함.
 *  이미 구현된 코드 일부를 책임 분리한 것. 분리된 코드는 이미 충분히 테스트가 되고 있던 상황.
 *
 *  하지만, StarkIndustriesProductImporter 경우에는 StarkIndustriesProductTranslator 염두하고 테스트를 했고
 *  mock 을 사용했다 -> StarkIndustriesProductTranslator 가 해야되는 일은 구현하지 않은 상태에서!
 *
 *  ProductImporter 를 구현하고 나서 어떤 결과물을 얻었는지가 각각 다르다.
 *
 *  2. WayneEnterprises 보다 StarkIndustries 경우, 테스트 코드가 약간 더 복잡하다.
 *  -> sut 구현에 대한 지식이 테스트 코드에도 있기 때문.
 *  WayneEnterprises 가 Translator 를 별도로 분리를 했는지아닌지는 관심대상도 아니고 알 지도 못한다. 걍 public interface 가 제대로 동작하는지만 관심이있다.
 *  StarkIndustries 는 Translator 가 어떻게 쓰이는지 추측을 했어야한다. 이렇게 구현될 거니까, 테스트도 이렇게 구현되는구나. 그래서 더 장황하고 복잡하게 느껴질 수 있다.
 * */