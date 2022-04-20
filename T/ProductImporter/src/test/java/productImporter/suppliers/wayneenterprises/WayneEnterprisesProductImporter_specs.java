package productImporter.suppliers.wayneenterprises;

import org.junit.jupiter.params.ParameterizedTest;
import productImporter.DomainArgumentsSource;
import productImporter.Product;

import static org.assertj.core.api.Assertions.assertThat;

public class WayneEnterprisesProductImporter_specs {

    @ParameterizedTest
    @DomainArgumentsSource /** 직접 만든 애노테이션, 테스트 메소드 매개변수로 배열 값을 넘겨주는 역할을 하고 있다 */
    void sut_projects_all_products(WayneEnterprisesProduct[] source) {
        var stub = new WayneEnterprisesProductSourceStub(source);
        var sut = new WayneEnterprisesProductImporter(stub); // 간접입력

        Iterable<Product> actual = sut.fetchProducts();

        assertThat(actual).hasSize(source.length);
    }
}

/**
 * [Step1. WayneEnterprises 사가 가지고 있는 상품 목록만큼 우리 커머스 서비스가 규정하고 있는 상품 포맷을 통과한 개수가 나와야한다.
 *      WayneEnterprisesProductSourceStub 을 만들어야한다.
 * */
