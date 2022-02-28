package productimporter.suppliers.wayneenterprises;

import org.junit.jupiter.params.ParameterizedTest;
import productimporter.DomainArgumentsSource;
import productimporter.Product;

import static org.assertj.core.api.Assertions.assertThat;

public class WayneEnterprisesProductImporter_specs {

    // Wayne 사의 모든 상품수(ProductSource)를 반환하면 그 상품 갯수 만큼, 우리 commercial 가 규정하고 있는 상품 포맷에 맞는 상품들이 반환되어야 함을 테스트
    @ParameterizedTest
    @DomainArgumentsSource // 직접 만든 어노테이션
    void sut_projects_all_products(WayneEnterprisesProduct[] source) {
        var stub = new WayneEnterprisesProductSourceStub(source);
        var sut = new WayneEnterprisesProductImporter(stub);  // 테스트 메소드 파라미터로 입력받은 source 그대로를 테스트 대상 sut에 제공하도록 간접 입력

        Iterable<Product> actual = sut.fetchProducts();
        assertThat(actual).hasSize(source.length);
    }

    // WayneEnterprises의 상품 supplierName을 항상 "Wayne" 이라고 설정되어있음을 확인하는 테스트
    @ParameterizedTest
    @DomainArgumentsSource
    void sut_correctly_sets_supplier_name(WayneEnterprisesProduct[] source){
        var stub = new WayneEnterprisesProductSourceStub(source);
        var sut = new WayneEnterprisesProductImporter(stub);

        Iterable<Product> actual = sut.fetchProducts();

        assertThat(actual).allMatch(x->x.getSupplierName().equals("WAYNE"));
    }


}
