package productimporter.suppliers.wayneenterprises;

import org.junit.jupiter.params.ParameterizedTest;
import productimporter.DomainArgumentsSource;
import productimporter.Product;

import static org.assertj.core.api.Assertions.assertThat;

public class WayneEnterprisesProductImporter_specs {

    @ParameterizedTest
    @DomainArgumentsSource
    void sut_projects_all_products(WayneEnterprisesProduct[] source) {
        var stub = new WayneEnterprisesProductSourceStub(source);
        var sut = new WayneEnterprisesProductImporter(stub);

        Iterable<Product> actual = sut.fetchProducts();
        assertThat(actual).hasSize(source.length);
    }


}
