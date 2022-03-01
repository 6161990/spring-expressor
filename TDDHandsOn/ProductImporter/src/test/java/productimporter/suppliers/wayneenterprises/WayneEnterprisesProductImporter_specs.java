package productimporter.suppliers.wayneenterprises;

import org.junit.jupiter.params.ParameterizedTest;
import productimporter.DomainArgumentsSource;
import productimporter.Product;

import java.util.ArrayList;
import java.util.List;

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

    // WayneEnterprises의 상품들을 잘 투영하는지 확인하는 테스트
    @ParameterizedTest
    @DomainArgumentsSource
    void sut_correctly_projects_source_properties(WayneEnterprisesProduct source){
        var stub = new WayneEnterprisesProductSourceStub(source);
        var sut = new WayneEnterprisesProductImporter(stub);

        List<Product> products = new ArrayList<>();
        sut.fetchProducts().forEach(products::add);
        Product actual = products.get(0);

        assertThat(actual.getProductCode()).isEqualTo(source.getId()); // Fail 1
        assertThat(actual.getProductName()).isEqualTo(source.getTitle()); // Fail 2
        assertThat(actual.getPricing().getListPrice()).isEqualByComparingTo(Integer.toString(source.getListPrice())); // Fail 3
        assertThat(actual.getPricing().getDiscount()).isEqualByComparingTo(Integer.toString(source.getListPrice()-source.getSellingPrice())); // Fail 4

       /**
        * 커머스 업체인 우리는 Product 가격을 정가와 할인가로 분류.
        * 공급 업체인 WayneEnterprises는 상품(Source)를 정가와 판매가로 분류. (할인가를 제공해주지 않기 때문에 정가-판매가 로 할인가 계산)
        *
        * BigDecimal 의 비교 isEqualByComparingTo
        * compareTo( ) 와 동일하기 때문에 소수점 마지막 0이 달라도 true
        *
        * Fail 1
        * Expecting : null
        * to be equal to : id5556d543-9a1f-4c84-a06a-77d71d1375f0
        *
        * Fail 2
        * Expecting : null
        * to be equal to : title1c120845-4fd0-4da2-a484-66a4806e16a9


        여기까지의 배움,,!
        Test 진행 시, WayneEnterprisesProductSource 서비스를 구동시키거나, 종료시킨 적이 없음. 불가능한 일임.
        DOC 로 대체하여 Test 를 진행함. 여기서 DOC는 Stub: WayneEnterprisesProductSourceStub를 이용했다.


        */

    }

}
