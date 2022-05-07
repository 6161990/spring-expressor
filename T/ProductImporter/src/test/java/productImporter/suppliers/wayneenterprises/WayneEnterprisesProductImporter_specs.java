package productImporter.suppliers.wayneenterprises;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import productImporter.DomainArgumentsSource;
import productImporter.Product;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WayneEnterprisesProductImporter_specs {

    @DisplayName("WayneEnterprises 사가 넘겨준 상품 수 만큼 커머스 서비스에 등록된다")
    @ParameterizedTest
    @DomainArgumentsSource /** 직접 만든 애노테이션, 테스트 메소드 매개변수로 배열 값을 넘겨주는 역할을 하고 있다 */
    void sut_projects_all_products(WayneEnterprisesProduct[] source) {
        var stub = new WayneEnterprisesProductSourceStub(source);
        var sut = new WayneEnterprisesProductImporter(stub);

        Iterable<Product> actual = sut.fetchProducts();
        assertThat(actual).hasSize(source.length);
    }

    @DisplayName("WayneEnterprises 사의 Supplier 의 이름을 Importer 에서 WAYNE 이라고 설정한다")
    @ParameterizedTest
    @DomainArgumentsSource
    void sut_correctly_sets_supplier_name(WayneEnterprisesProduct[] source) {
        var stub = new WayneEnterprisesProductSourceStub(source);
        var sut = new WayneEnterprisesProductImporter(stub);

        Iterable<Product> actual = sut.fetchProducts();
        assertThat(actual).allMatch(x->x.getSupplierName().equals("WAYNE"));
    }

    @DisplayName("WayneEnterprises 사의 properties 들을 잘 투영한다.")
    @ParameterizedTest
    @DomainArgumentsSource
    void sut_correctly_projects_source_properties(WayneEnterprisesProduct source){
        var stub = new WayneEnterprisesProductSourceStub(source);
        var sut = new WayneEnterprisesProductImporter(stub);

        List<Product> products = new ArrayList<>();
        sut.fetchProducts().forEach(products::add);
        Product actual = products.get(0);

        assertThat(actual.getProductCode()).isEqualTo(source.getId());
        assertThat(actual.getProductName()).isEqualTo(source.getTitle());
        assertThat(actual.getPricing().getListPrice()).isEqualByComparingTo(Integer.toString(source.getListPrice()));
        assertThat(actual.getPricing().getDiscount()).isEqualByComparingTo(Integer.toString(source.getListPrice() -source.getSellingPrice()));
    }

}

